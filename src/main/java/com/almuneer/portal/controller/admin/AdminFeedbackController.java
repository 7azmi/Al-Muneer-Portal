package com.almuneer.portal.controller.admin;

import com.almuneer.portal.model.Feedback;
import com.almuneer.portal.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/feedback")
@RequiredArgsConstructor
public class AdminFeedbackController {

    private final FeedbackService feedbackService;

    /** UC015 — list all feedback */
    @GetMapping
    public String listFeedback(Model model) {
        List<Feedback> feedbacks = feedbackService.getAll();
        model.addAttribute("feedbacks", feedbacks);
        return "admin/feedback-manage";
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
