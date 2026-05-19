package com.almuneer.portal.controller;

import com.almuneer.portal.model.AvailabilitySlot;
import com.almuneer.portal.model.BookingInquiry;
import com.almuneer.portal.model.PricingTier;
import com.almuneer.portal.model.enums.SlotStatus;
import com.almuneer.portal.service.AvailabilitySlotService;
import com.almuneer.portal.service.BookingInquiryService;
import com.almuneer.portal.service.PricingTierService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/inquiry")
@RequiredArgsConstructor
public class InquiryController {

    private final BookingInquiryService inquiryService;
    private final AvailabilitySlotService slotService;
    private final PricingTierService pricingTierService;

    /** UC005 — show inquiry form, optionally pre-filled with a date */
    @GetMapping
    public String showForm(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {
        List<PricingTier> tiers = pricingTierService.getActiveTiers();
        model.addAttribute("tiers", tiers);
        model.addAttribute("selectedDate", date);
        return "visitor/inquiry-form";
    }

    /** UC005 — process submission */
    @PostMapping("/submit")
    public String submit(
            @RequestParam String visitorName,
            @RequestParam String visitorWhatsApp,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eventDate,
            @RequestParam(required = false) String eventType,
            @RequestParam(required = false) Integer numGuests,
            @RequestParam(required = false) Long pricingId,
            @RequestParam(required = false) String message,
            RedirectAttributes redirectAttributes) {

        // Validate date availability
        AvailabilitySlot slot = slotService.getOrCreateSlot(eventDate);
        if (slot.getStatus() == SlotStatus.PENDING || slot.getStatus() == SlotStatus.BOOKED) {
            redirectAttributes.addFlashAttribute("error",
                    "The selected date (" + eventDate + ") is not available. Please choose another date.");
            return "redirect:/inquiry?date=" + eventDate;
        }

        PricingTier tier = (pricingId != null) ? pricingTierService.getTierById(pricingId) : null;

        BookingInquiry inquiry = BookingInquiry.builder()
                .visitorName(visitorName.trim())
                .visitorWhatsApp(visitorWhatsApp.trim())
                .slot(slot)
                .pricingTier(tier)
                .eventType(eventType)
                .numGuests(numGuests)
                .message(message)
                .build();

        BookingInquiry saved = inquiryService.submitInquiry(inquiry);
        return "redirect:/inquiry/confirmation/" + saved.getInquiryId();
    }

    /** UC005 — confirmation screen with Inquiry ID */
    @GetMapping("/confirmation/{id}")
    public String confirmation(@PathVariable Long id, Model model) {
        BookingInquiry inquiry = inquiryService.getById(id);
        model.addAttribute("inquiry", inquiry);
        return "visitor/inquiry-confirmation";
    }
}
