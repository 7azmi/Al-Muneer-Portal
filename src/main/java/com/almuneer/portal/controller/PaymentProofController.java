package com.almuneer.portal.controller;

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
    private final FileUploadUtil fileUploadUtil;

    /** UC011 — show payment proof upload form */
    @GetMapping("/upload")
    public String showForm(@RequestParam(required = false) Long inquiryId, Model model) {
        model.addAttribute("inquiryId", inquiryId);
        return "visitor/payment-proof";
    }

    /** UC011 — handle proof upload */
    @PostMapping("/upload")
    public String uploadProof(
            @RequestParam Long inquiryId,
            @RequestParam("proofFile") MultipartFile proofFile,
            RedirectAttributes redirectAttributes) {

        try {
            String filePath = fileUploadUtil.savePaymentProof(proofFile);
            paymentProofService.submitProof(inquiryId, proofFile.getOriginalFilename(), filePath);
            redirectAttributes.addFlashAttribute("success",
                    "Payment proof uploaded successfully. The admin will review it shortly.");
            return "redirect:/payment/upload?inquiryId=" + inquiryId;
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/payment/upload?inquiryId=" + inquiryId;
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "File upload failed. Please try again.");
            return "redirect:/payment/upload?inquiryId=" + inquiryId;
        }
    }
}
