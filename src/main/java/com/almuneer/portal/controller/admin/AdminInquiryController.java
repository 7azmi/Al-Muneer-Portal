package com.almuneer.portal.controller.admin;

import com.almuneer.portal.model.BookingInquiry;
import com.almuneer.portal.model.enums.InquiryStatus;
import com.almuneer.portal.model.enums.SlotStatus;
import com.almuneer.portal.service.AvailabilitySlotService;
import com.almuneer.portal.service.BookingInquiryService;
import com.almuneer.portal.service.NotificationTemplateService;
import com.almuneer.portal.util.DeepLinkBuilderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/inquiries")
@RequiredArgsConstructor
public class AdminInquiryController {

    private final BookingInquiryService inquiryService;
    private final AvailabilitySlotService slotService;
    private final NotificationTemplateService templateService;
    private final DeepLinkBuilderUtil deepLinkBuilder;

    /** Statuses considered "closed" — hidden by default */
    private static final List<InquiryStatus> CLOSED_STATUSES = List.of(
            InquiryStatus.COMPLETED,
            InquiryStatus.CANCELLED_BY_ADMIN,
            InquiryStatus.CANCELLED_BY_VISITOR
    );

    /** UC009 — list inquiries with optional status filter */
    @GetMapping
    public String listInquiries(
            @RequestParam(required = false) String filter, // null = active only, "all" = show all, or a specific status name
            Model model) {

        List<BookingInquiry> inquiries;
        String activeFilter;

        if ("all".equals(filter)) {
            inquiries = inquiryService.getAll();
            activeFilter = "all";
        } else if (filter != null && !filter.isBlank()) {
            try {
                inquiries = inquiryService.getByStatus(InquiryStatus.valueOf(filter));
            } catch (IllegalArgumentException e) {
                inquiries = inquiryService.getFiltered(CLOSED_STATUSES);
            }
            activeFilter = filter;
        } else {
            // Default: hide completed/cancelled
            inquiries = inquiryService.getFiltered(CLOSED_STATUSES);
            activeFilter = "active";
        }

        // Counts per status keyed by name (String) — Thymeleaf can't index by enum
        Map<String, Long> counts = new java.util.LinkedHashMap<>();
        List<BookingInquiry> all = inquiryService.getAll();
        for (InquiryStatus s : InquiryStatus.values()) {
            counts.put(s.name(), all.stream().filter(i -> i.getStatus() == s).count());
        }

        model.addAttribute("inquiries", inquiries);
        model.addAttribute("statuses", InquiryStatus.values());
        model.addAttribute("counts", counts);
        model.addAttribute("activeFilter", activeFilter);
        model.addAttribute("closedStatuses", CLOSED_STATUSES);
        return "admin/inquiries-manage";
    }

    /** UC009 — view single inquiry detail */
    @GetMapping("/{id}")
    public String viewInquiry(@PathVariable Long id, Model model) {
        inquiryService.markViewed(id);
        BookingInquiry inquiry = inquiryService.getById(id);
        model.addAttribute("inquiry", inquiry);
        model.addAttribute("templates", templateService.getAll());
        model.addAttribute("statuses", InquiryStatus.values());
        model.addAttribute("visitorWa", inquiry.getVisitorWhatsApp().replaceAll("[^\\d]", ""));
        model.addAttribute("visitorName", inquiry.getVisitorName());
        model.addAttribute("inquiryId", inquiry.getReferenceCode());
        model.addAttribute("eventDate",
                inquiry.getSlot() != null ? inquiry.getSlot().getSlotDate().toString() : "");
        model.addAttribute("inquiryStatus", inquiry.getStatus().name());
        return "admin/inquiry-detail";
    }

    /** UC009 — update inquiry status / notes */
    @PostMapping("/{id}/update-status")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestParam(required = false) String adminNotes,
            RedirectAttributes redirectAttributes) {
        inquiryService.updateStatus(id, InquiryStatus.valueOf(status), adminNotes);
        redirectAttributes.addFlashAttribute("success", "Inquiry status updated.");
        return "redirect:/admin/inquiries/" + id;
    }

    /** AJAX: manually override slot status */
    @PostMapping("/set-slot-status")
    @ResponseBody
    public ResponseEntity<Map<String, String>> setSlotStatus(
            @RequestParam String date,
            @RequestParam String status,
            @RequestParam(required = false) String notes) {
        slotService.setSlotStatus(LocalDate.parse(date), SlotStatus.valueOf(status), notes);
        return ResponseEntity.ok(Map.of("status", "success"));
    }
}
