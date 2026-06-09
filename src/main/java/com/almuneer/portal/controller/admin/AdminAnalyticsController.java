package com.almuneer.portal.controller.admin;

import com.almuneer.portal.repository.PageVisitRepository;
import com.almuneer.portal.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/analytics")
@RequiredArgsConstructor
public class AdminAnalyticsController {

    private final PageVisitRepository pageVisitRepository;
    private final GeminiService geminiService;

    /** Renders the page immediately — no Gemini wait. */
    @GetMapping
    public String analytics(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            Model model) {

        Long totalHits;
        List<Object[]> perPage;
        List<Object[]> daily;

        if (fromDate != null && toDate != null) {
            totalHits = pageVisitRepository.sumAllHitsBetween(fromDate, toDate);
            perPage = pageVisitRepository.findTotalHitsPerPageBetween(fromDate, toDate);
            daily = pageVisitRepository.findDailyHitsBetween(fromDate, toDate);
        } else {
            totalHits = pageVisitRepository.sumAllHits();
            perPage = pageVisitRepository.findTotalHitsPerPage();
            daily = pageVisitRepository.findDailyHitsSince(LocalDate.now().minusDays(29));
        }

        model.addAttribute("totalHits", totalHits != null ? totalHits : 0L);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);

        Map<String, Long> pageMap = new LinkedHashMap<>();
        for (Object[] row : perPage) {
            pageMap.put((String) row[0], ((Number) row[1]).longValue());
        }
        model.addAttribute("pageHits", pageMap);

        Map<String, Long> dailyMap = new LinkedHashMap<>();
        for (Object[] row : daily) {
            dailyMap.put(row[0].toString(), ((Number) row[1]).longValue());
        }
        model.addAttribute("dailyHits", dailyMap);

        return "admin/analytics";
    }

    /**
     * Async endpoint: browser fetches this after page load.
     * Analyzes the traffic funnel and returns conversion-focused advice.
     */
    @GetMapping("/ai-insight")
    @ResponseBody
    public String aiInsight(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        Long totalHits;
        List<Object[]> perPage;

        if (fromDate != null && toDate != null) {
            totalHits = pageVisitRepository.sumAllHitsBetween(fromDate, toDate);
            perPage = pageVisitRepository.findTotalHitsPerPageBetween(fromDate, toDate);
        } else {
            totalHits = pageVisitRepository.sumAllHits();
            perPage = pageVisitRepository.findTotalHitsPerPage();
        }

        if (totalHits == null || totalHits == 0) {
            return "No traffic data available yet for this period.";
        }

        // Build a map of page → hits so we can compute funnel ratios
        Map<String, Long> pageMap = new LinkedHashMap<>();
        for (Object[] row : perPage) {
            pageMap.put((String) row[0], ((Number) row[1]).longValue());
        }

        String pageList = pageMap.entrySet().stream()
                .map(e -> String.format("%s: %d visits", e.getKey(), e.getValue()))
                .collect(Collectors.joining("\n"));

        // Pull out key funnel pages by matching URL patterns
        long homeHits    = pageMap.entrySet().stream().filter(e -> e.getKey().equals("/") || e.getKey().equals("/home")).mapToLong(Map.Entry::getValue).sum();
        long galleryHits = pageMap.entrySet().stream().filter(e -> e.getKey().contains("gallery")).mapToLong(Map.Entry::getValue).sum();
        long inquiryHits = pageMap.entrySet().stream().filter(e -> e.getKey().contains("inquiry")).mapToLong(Map.Entry::getValue).sum();
        long pricingHits = pageMap.entrySet().stream().filter(e -> e.getKey().contains("pricing")).mapToLong(Map.Entry::getValue).sum();

        String prompt = String.format(
                "You are a digital marketing advisor for Al-Muneer Hall, a wedding venue website in Ibb, Yemen. " +
                "Analyze this visitor traffic data and give exactly 3 bullet points (use • as bullet). " +
                "Focus on the booking funnel: do visitors reach the inquiry page? Which pages attract vs. lose visitors? " +
                "Be specific — reference actual page names and visit counts. End each bullet with a concrete action.\n\n" +
                "Period total: %d visits\n" +
                "Funnel breakdown — Home: %d | Gallery: %d | Pricing: %d | Inquiry: %d\n\n" +
                "All pages:\n%s\n\n" +
                "Bullet 1: Most and least visited pages — what does this say about visitor interest?\n" +
                "Bullet 2: Funnel drop-off — how many visitors reach the inquiry page vs. the home page? Is this healthy?\n" +
                "Bullet 3: One concrete thing the owner can change on the website to increase inquiries.",
                totalHits, homeHits, galleryHits, pricingHits, inquiryHits, pageList
        );

        return geminiService.generate(prompt);
    }
}
