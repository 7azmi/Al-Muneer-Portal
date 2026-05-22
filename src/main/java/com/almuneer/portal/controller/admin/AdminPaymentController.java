package com.almuneer.portal.controller.admin;

import com.almuneer.portal.model.BookingInquiry;
import com.almuneer.portal.model.NotificationTemplate;
import com.almuneer.portal.model.PaymentProof;
import com.almuneer.portal.model.enums.VerificationStatus;
import com.almuneer.portal.service.BookingInquiryService;
import com.almuneer.portal.service.NotificationTemplateService;
import com.almuneer.portal.service.PaymentProofService;
import com.almuneer.portal.util.DeepLinkBuilderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/payments")
@RequiredArgsConstructor
public class AdminPaymentController {

    private final PaymentProofService paymentProofService;
    private final BookingInquiryService inquiryService;
    private final NotificationTemplateService templateService;
    private final DeepLinkBuilderUtil deepLinkBuilder;

    /** UC013 — list all payment proofs */
    @GetMapping
    public String listProofs(Model model) {
        List<PaymentProof> proofs = paymentProofService.getAll();
        model.addAttribute("proofs", proofs);
        return "admin/payment-manage";
    }

    /** UC013 — view single proof detail with verify/reject actions */
    @GetMapping("/{id}")
    public String viewProof(@PathVariable Long id, Model model) {
        PaymentProof proof = paymentProofService.getById(id);
        model.addAttribute("proof", proof);
        model.addAttribute("templates", templateService.getAll());
        // Visitor context for JS deep-link builder
        BookingInquiry inquiry = proof.getInquiry();
        model.addAttribute("visitorWa", inquiry.getVisitorWhatsApp().replaceAll("[^\\d]", ""));
        model.addAttribute("visitorName", inquiry.getVisitorName());
        model.addAttribute("inquiryId", inquiry.getInquiryId());
        model.addAttribute("eventDate",
                inquiry.getSlot() != null ? inquiry.getSlot().getSlotDate().toString() : "");
        model.addAttribute("proofStatus", proof.getVerificationStatus().name());
        return "admin/payment-detail";
    }

    /** UC013 — update proof status (Verified / Rejected) */
    @PostMapping("/{id}/update-status")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestParam(required = false) String adminNotes,
            RedirectAttributes redirectAttributes) {
        paymentProofService.updateStatus(id, VerificationStatus.valueOf(status), adminNotes);
        redirectAttributes.addFlashAttribute("success", "Payment proof status updated.");
        return "redirect:/admin/payments/" + id;
    }

    private String buildLink(BookingInquiry inquiry, String eventName, PaymentProof proof) {
        try {
            NotificationTemplate template = templateService.getByEventName(eventName);
            return deepLinkBuilder.buildLink(
                    inquiry.getVisitorWhatsApp(),
                    template.getTemplateText(),
                    Map.of(
                            "visitorName", inquiry.getVisitorName(),
                            "inquiryId", String.valueOf(inquiry.getInquiryId()),
                            "eventDate", inquiry.getSlot() != null ? inquiry.getSlot().getSlotDate().toString() : "",
                            "status", proof.getVerificationStatus().name()
                    ));
        } catch (Exception e) {
            return "https://wa.me/" + inquiry.getVisitorWhatsApp().replaceAll("[^\\d]", "");
        }
    }
}
