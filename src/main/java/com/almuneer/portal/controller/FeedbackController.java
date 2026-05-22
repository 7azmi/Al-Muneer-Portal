package com.almuneer.portal.controller;

import com.almuneer.portal.model.Feedback;
import com.almuneer.portal.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Value("${app.country.code:+967}")
    private String countryCode;

    /** Strip non-digits and ensure the configured country code prefix is applied */
    private String normaliseWhatsApp(String raw) {
        String digits = raw.replaceAll("[^\\d]", "");
        String codeDigits = countryCode.replaceAll("[^\\d]", "");
        if (!digits.startsWith(codeDigits)) {
            digits = codeDigits + digits;
        }
        return "+" + digits;
    }

    /** UC012 — show feedback form */
    @GetMapping
    public String showForm(Model model) {
        return "visitor/feedback";
    }

    /** UC012 — process feedback submission */
    @PostMapping("/submit")
    public String submit(
            @RequestParam String visitorName,
            @RequestParam String visitorWhatsApp,
            @RequestParam String feedbackText,
            @RequestParam(defaultValue = "5") Integer rating,
            RedirectAttributes redirectAttributes) {

        if (visitorName.isBlank() || visitorWhatsApp.isBlank() || feedbackText.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "All fields are required.");
            return "redirect:/feedback";
        }

        Feedback feedback = Feedback.builder()
                .visitorName(visitorName.trim())
                .visitorWhatsApp(normaliseWhatsApp(visitorWhatsApp.trim()))
                .feedbackText(feedbackText.trim())
                .rating(Math.max(1, Math.min(5, rating)))
                .isReviewed(false)
                .build();

        feedbackService.submit(feedback);
        redirectAttributes.addFlashAttribute("success", "Thank you for your feedback!");
        return "redirect:/feedback";
    }
}

