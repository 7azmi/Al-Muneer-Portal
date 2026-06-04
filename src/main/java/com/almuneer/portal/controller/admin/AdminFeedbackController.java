package com.almuneer.portal.controller.admin;

import com.almuneer.portal.model.Feedback;
import com.almuneer.portal.service.FeedbackService;
import com.almuneer.portal.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/feedback")
@RequiredArgsConstructor
public class AdminFeedbackController {

    private final FeedbackService feedbackService;
    private final GeminiService geminiService;

    /** UC015 — list all feedback. Page loads immediately; AI is fetched async by the browser. */
    @GetMapping
    public String listFeedback(Model model) {
        model.addAttribute("feedbacks", feedbackService.getAll());
        return "admin/feedback-manage";
    }

    /**
     * Async endpoint: browser fetches this after page load.
     * Analyzes the latest feedback and returns targeted advice for the owner.
     */
    @GetMapping("/ai-insight")
    @ResponseBody
    public String aiInsight() {
        List<Feedback> feedbacks = feedbackService.getAll();
        if (feedbacks.isEmpty()) {
            return "No feedback submitted yet — nothing to analyze.";
        }

        long total      = feedbacks.size();
        long unreviewed = feedbacks.stream().filter(f -> !f.getIsReviewed()).count();
        double avgRating = feedbacks.stream().mapToInt(Feedback::getRating).average().orElse(0);
        long lowCount   = feedbacks.stream().filter(f -> f.getRating() <= 2).count();

        // Recent complaints (low ratings) get their own section so Gemini can address them
        String complaints = feedbacks.stream()
                .filter(f -> f.getRating() <= 2)
                .limit(5)
                .map(f -> String.format("[%d★] %s", f.getRating(), f.getFeedbackText()))
                .collect(Collectors.joining("\n"));

        // Sample of positive feedback
        String positives = feedbacks.stream()
                .filter(f -> f.getRating() >= 4)
                .limit(10)
                .map(f -> String.format("[%d★] %s", f.getRating(), f.getFeedbackText()))
                .collect(Collectors.joining("\n"));

        String prompt = String.format(
                "You are a customer experience advisor for Al-Muneer Hall, a wedding venue in Ibb, Yemen. " +
                "Analyze this feedback data and give the owner exactly 3 bullet points (use • as bullet).\n" +
                "Rules: be direct and specific, reference real quotes or numbers, no generic phrases.\n\n" +
                "Stats: %d total reviews | Average rating: %.1f/5 | Low ratings (1-2★): %d | Unreviewed: %d\n\n" +
                "Complaints (1-2★ reviews):\n%s\n\n" +
                "Positive highlights (4-5★ reviews, sample):\n%s\n\n" +
                "Bullet 1: What guests consistently love — quote or paraphrase the pattern.\n" +
                "Bullet 2: The most urgent complaint or concern to fix — be specific about what went wrong.\n" +
                "Bullet 3: One concrete action the owner should take this week based on the above.",
                total, avgRating, lowCount, unreviewed,
                complaints.isEmpty() ? "None" : complaints,
                positives.isEmpty() ? "None" : positives
        );

        return geminiService.generate(prompt);
    }

    /** UC015 — view full feedback message */
    @GetMapping("/{id}")
    public String viewFeedback(@PathVariable Long id, Model model) {
        model.addAttribute("feedback", feedbackService.getById(id));
        return "admin/feedback-detail";
    }

    /** UC015 — mark feedback as reviewed with optional notes */
    @PostMapping("/{id}/review")
    public String markReviewed(
            @PathVariable Long id,
            @RequestParam(required = false) String adminNotes,
            @RequestParam(required = false, defaultValue = "list") String from,
            RedirectAttributes redirectAttributes) {
        feedbackService.markReviewed(id, adminNotes);
        redirectAttributes.addFlashAttribute("success", "Feedback marked as reviewed.");
        if ("detail".equals(from)) {
            return "redirect:/admin/feedback/" + id;
        }
        return "redirect:/admin/feedback";
    }
}

