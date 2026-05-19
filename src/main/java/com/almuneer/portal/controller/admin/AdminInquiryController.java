package com.almuneer.portal.controller.admin;

import com.almuneer.portal.model.BookingInquiry;
import com.almuneer.portal.model.NotificationTemplate;
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

    /** UC009 — list all inquiries */
    @GetMapping
    public String listInquiries(Model model) {
        List<BookingInquiry> inquiries = inquiryService.getAll();
        model.addAttribute("inquiries", inquiries);
        model.addAttribute("statuses", InquiryStatus.values());
        return "admin/inquiries-manage";
    }

    /** UC009 — view single inquiry detail (marks as VIEWED if NEW) */
    @GetMapping("/{id}")
    public String viewInquiry(@PathVariable Long id, Model model) {
        inquiryService.markViewed(id);
        BookingInquiry inquiry = inquiryService.getById(id);
        model.addAttribute("inquiry", inquiry);

        // Generate WhatsApp deep-link using INQUIRY_RECEIVED template
        String waLink = buildWhatsAppLink(inquiry, "INQUIRY_RECEIVED");
        model.addAttribute("waLink", waLink);
        model.addAttribute("statuses", InquiryStatus.values());
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

    /** UC009 — AJAX: manually override slot status */
    @PostMapping("/set-slot-status")
    @ResponseBody
    public ResponseEntity<Map<String, String>> setSlotStatus(
            @RequestParam String date,
            @RequestParam String status,
            @RequestParam(required = false) String notes) {
        slotService.setSlotStatus(LocalDate.parse(date), SlotStatus.valueOf(status), notes);
        return ResponseEntity.ok(Map.of("status", "success"));
    }

    private String buildWhatsAppLink(BookingInquiry inquiry, String eventName) {
        try {
            NotificationTemplate template = templateService.getByEventName(eventName);
            return deepLinkBuilder.buildLink(
                    inquiry.getVisitorWhatsApp(),
                    template.getTemplateText(),
                    Map.of(
                            "visitorName", inquiry.getVisitorName(),
                            "inquiryId", String.valueOf(inquiry.getInquiryId()),
                            "eventDate", inquiry.getSlot() != null ? inquiry.getSlot().getSlotDate().toString() : "",
                            "status", inquiry.getStatus().name()
                    ));
        } catch (Exception e) {
            return "https://wa.me/" + inquiry.getVisitorWhatsApp().replaceAll("[^\\d]", "");
        }
    }
}
