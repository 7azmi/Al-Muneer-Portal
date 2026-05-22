package com.almuneer.portal.controller;

import com.almuneer.portal.model.BookingInquiry;
import com.almuneer.portal.service.BookingInquiryService;
import com.almuneer.portal.service.PaymentProofService;
import com.almuneer.portal.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentProofController {

    private final PaymentProofService paymentProofService;
    private final BookingInquiryService inquiryService;
    private final FileUploadUtil fileUploadUtil;

    /**
     * UC011 — show payment proof upload form.
     * Accepts either inquiryId (internal PK, legacy) or referenceCode (visitor-facing).
     */
    @GetMapping("/upload")
    public String showForm(
            @RequestParam(required = false) Long inquiryId,
            @RequestParam(required = false) Long referenceCode,
            Model model) {

        if (referenceCode != null) {
            // Resolve referenceCode → internal inquiryId
            BookingInquiry inq = inquiryService.findByReferenceCode(referenceCode)
                    .orElse(null);
            if (inq != null) {
                model.addAttribute("inquiryId", inq.getInquiryId());
                model.addAttribute("referenceCode", referenceCode);
                model.addAttribute("visitorName", inq.getVisitorName());
                model.addAttribute("eventDate", inq.getSlot() != null ? inq.getSlot().getSlotDate() : null);
            } else {
                model.addAttribute("error", "No inquiry found for reference code " + referenceCode + ".");
            }
        } else {
            model.addAttribute("inquiryId", inquiryId);
        }
        return "visitor/payment-proof";
    }

    /** UC011 — handle proof upload */
    @PostMapping("/upload")
    public String uploadProof(
            @RequestParam Long inquiryId,
            @RequestParam(required = false) Long referenceCode,
            @RequestParam("proofFile") MultipartFile proofFile,
            RedirectAttributes redirectAttributes) {

        try {
            String savedName = fileUploadUtil.savePaymentProof(proofFile);
            // savedName is the UUID filename (e.g. "abc123.jpg");
            // it is served via /uploads/proofs/{savedName}
            paymentProofService.submitProof(inquiryId, savedName, savedName);
            redirectAttributes.addFlashAttribute("success",
                    "Payment proof uploaded successfully. The admin will review it shortly.");

            if (referenceCode != null) {
                return "redirect:/inquiry/confirmation/" + referenceCode;
            }
            return "redirect:/payment/upload?inquiryId=" + inquiryId;

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return referenceCode != null
                    ? "redirect:/payment/upload?referenceCode=" + referenceCode
                    : "redirect:/payment/upload?inquiryId=" + inquiryId;
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "File upload failed. Please try again.");
            return referenceCode != null
                    ? "redirect:/payment/upload?referenceCode=" + referenceCode
                    : "redirect:/payment/upload?inquiryId=" + inquiryId;
        }
    }
}
