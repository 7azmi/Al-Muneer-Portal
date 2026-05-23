package com.almuneer.portal.controller;

import com.almuneer.portal.model.AvailabilitySlot;
import com.almuneer.portal.model.BookingInquiry;
import com.almuneer.portal.model.PricingTier;
import com.almuneer.portal.model.enums.SlotStatus;
import com.almuneer.portal.service.AvailabilitySlotService;
import com.almuneer.portal.service.BookingInquiryService;
import com.almuneer.portal.service.PricingTierService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/inquiry")
@RequiredArgsConstructor
public class InquiryController {

    private final BookingInquiryService inquiryService;
    private final AvailabilitySlotService slotService;
    private final PricingTierService pricingTierService;

    @Value("${app.country.code:+967}")
    private String countryCode;

    private static final String COOKIE_NAME = "inq_ref";
    private static final int COOKIE_MAX_AGE = 150 * 24 * 60 * 60; // 150 days in seconds

    // ─── Normalise phone ───────────────────────────────────────────────────────

    private String normaliseWhatsApp(String raw) {
        String digits = raw.replaceAll("[^\\d]", "");
        String codeDigits = countryCode.replaceAll("[^\\d]", "");
        if (!digits.startsWith(codeDigits)) {
            digits = codeDigits + digits;
        }
        return "+" + digits;
    }

    // ─── Cookie helpers ────────────────────────────────────────────────────────

    /** Read the stored reference code from cookie, or null if absent. */
    private Long readRefCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;
        return Arrays.stream(request.getCookies())
                .filter(c -> COOKIE_NAME.equals(c.getName()))
                .map(c -> {
                    try { return Long.parseLong(c.getValue()); } catch (Exception e) { return null; }
                })
                .filter(v -> v != null)
                .findFirst().orElse(null);
    }

    private void writeRefCookie(HttpServletResponse response, long referenceCode) {
        Cookie cookie = new Cookie(COOKIE_NAME, String.valueOf(referenceCode));
        cookie.setMaxAge(COOKIE_MAX_AGE);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    private void clearRefCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(COOKIE_NAME, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    // ─── UC005: Landing page — new inquiry OR look up existing ─────────────────

    @GetMapping
    public String landing(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) Long pricingId,
            HttpServletRequest request,
            Model model) {

        Long savedRef = readRefCookie(request);
        BookingInquiry existing = null;
        if (savedRef != null) {
            existing = inquiryService.findByReferenceCode(savedRef).orElse(null);
        }

        PricingTier selectedTier = null;
        if (pricingId != null) {
            try {
                selectedTier = pricingTierService.getTierById(pricingId);
            } catch (IllegalArgumentException ignored) {
                // Unknown package id — ignore
            }
        }

        model.addAttribute("existingInquiry", existing);
        model.addAttribute("tiers", pricingTierService.getActiveTiers());
        model.addAttribute("selectedDate", date);
        model.addAttribute("selectedPricingId", pricingId);
        model.addAttribute("selectedTier", selectedTier);
        return "visitor/inquiry-landing";
    }

    // ─── UC005: Submit new inquiry ──────────────────────────────────────────────

    @PostMapping("/submit")
    public String submit(
            @RequestParam String visitorName,
            @RequestParam String visitorWhatsApp,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eventDate,
            @RequestParam(required = false) String eventType,
            @RequestParam(required = false) Integer numGuests,
            @RequestParam(required = false) Long pricingId,
            @RequestParam(required = false) String message,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes) {

        AvailabilitySlot slot = slotService.getOrCreateSlot(eventDate);
        if (slot.getStatus() == SlotStatus.PENDING || slot.getStatus() == SlotStatus.BOOKED) {
            redirectAttributes.addFlashAttribute("error",
                    "The selected date (" + eventDate + ") is not available. Please choose another date.");
            return "redirect:/inquiry?date=" + eventDate;
        }

        PricingTier tier = (pricingId != null) ? pricingTierService.getTierById(pricingId) : null;

        BookingInquiry inquiry = BookingInquiry.builder()
                .visitorName(visitorName.trim())
                .visitorWhatsApp(normaliseWhatsApp(visitorWhatsApp.trim()))
                .slot(slot)
                .pricingTier(tier)
                .eventType(eventType)
                .numGuests(numGuests)
                .message(message)
                .build();

        BookingInquiry saved = inquiryService.submitInquiry(inquiry);

        // Persist reference code as a 150-day cookie
        writeRefCookie(response, saved.getReferenceCode());

        return "redirect:/inquiry/confirmation/" + saved.getReferenceCode();
    }

    // ─── UC005: Confirmation page ───────────────────────────────────────────────

    @GetMapping("/confirmation/{refCode}")
    public String confirmation(@PathVariable Long refCode, Model model) {
        BookingInquiry inquiry = inquiryService.findByReferenceCode(refCode)
                .orElseThrow(() -> new IllegalArgumentException("Inquiry not found"));
        model.addAttribute("inquiry", inquiry);
        return "visitor/inquiry-confirmation";
    }

    // ─── Visitor lookup by reference code ──────────────────────────────────────

    @PostMapping("/lookup")
    public String lookup(
            @RequestParam Long referenceCode,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        Optional<BookingInquiry> found = inquiryService.findByReferenceCode(referenceCode);
        if (found.isEmpty()) {
            redirectAttributes.addFlashAttribute("error",
                    "No inquiry found with reference code " + referenceCode + ". Please check the number.");
            return "redirect:/inquiry";
        }
        // Refresh cookie with the looked-up code
        writeRefCookie(response, referenceCode);
        return "redirect:/inquiry/confirmation/" + referenceCode;
    }

    // ─── Visitor self-cancellation ──────────────────────────────────────────────

    @PostMapping("/cancel")
    public String cancel(
            @RequestParam Long referenceCode,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        try {
            inquiryService.cancelByVisitor(referenceCode);
            clearRefCookie(response);
            redirectAttributes.addFlashAttribute("success",
                    "Your inquiry has been cancelled. The date is now available again.");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/inquiry/confirmation/" + referenceCode;
        }
        return "redirect:/inquiry";
    }
}
