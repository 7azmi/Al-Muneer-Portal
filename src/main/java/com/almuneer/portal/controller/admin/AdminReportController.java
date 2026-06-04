package com.almuneer.portal.controller.admin;

import com.almuneer.portal.service.GeminiService;
import com.almuneer.portal.util.ReportGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/admin/reports")
@RequiredArgsConstructor
public class AdminReportController {

    private final ReportGeneratorUtil reportGenerator;
    private final GeminiService geminiService;

    /** Renders the page immediately — no Gemini wait. */
    @GetMapping
    public String reports(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            Model model) {
        model.addAttribute("report", reportGenerator.generateSummary(fromDate, toDate));
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        return "admin/reports";
    }

    /**
     * Async endpoint: browser fetches this after page load.
     * Returns a plain-text AI insight based on the current report numbers.
     */
    @GetMapping("/ai-insight")
    @ResponseBody
    public String aiInsight(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        Map<String, Object> r = reportGenerator.generateSummary(fromDate, toDate);

        long total     = toLong(r.get("totalInquiries"));
        long confirmed = toLong(r.get("confirmedBookings"));
        long cancelled = toLong(r.get("cancelledInquiries"));
        long pending   = toLong(r.get("proofsPending"));
        long rejected  = toLong(r.get("proofsRejected"));
        long unreviewed = toLong(r.get("unreviewed"));
        Object avgRating = r.get("averageRating");

        // Derive rates so the prompt contains useful context, not just raw counts
        String convRate = total > 0
                ? String.format("%.0f%%", (confirmed * 100.0 / total)) : "N/A";
        String cancRate = total > 0
                ? String.format("%.0f%%", (cancelled * 100.0 / total)) : "N/A";

        String prompt = String.format(
                "You are a business advisor for Al-Muneer Hall, a wedding venue in Ibb, Yemen. " +
                "The owner is reviewing this period's performance. Give exactly 3 bullet points (use • as bullet). " +
                "Each bullet must reference a specific number from the data and end with one concrete action the owner should take today or this week. " +
                "Do NOT give generic advice — every sentence must be tied to a real figure below.\n\n" +
                "Data:\n" +
                "• Total inquiries: %d | Booking conversion rate: %s | Cancellation rate: %s\n" +
                "• Payment proofs awaiting verification: %d | Rejected proofs: %d\n" +
                "• Unreviewed customer feedback: %d | Average customer rating: %s/5\n",
                total, convRate, cancRate,
                pending, rejected,
                unreviewed, avgRating
        );

        return geminiService.generate(prompt);
    }

    private long toLong(Object val) {
        return val instanceof Number ? ((Number) val).longValue() : 0L;
    }
}

