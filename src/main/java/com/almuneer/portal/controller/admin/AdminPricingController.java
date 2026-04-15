package com.almuneer.portal.controller.admin;

import com.almuneer.portal.model.PricingTier;
import com.almuneer.portal.service.PricingTierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/pricing")
@RequiredArgsConstructor
public class AdminPricingController {

    private final PricingTierService pricingTierService;

    @GetMapping
    public String managePricing(Model model) {
        model.addAttribute("tiers", pricingTierService.getAllTiers());
        model.addAttribute("newTier", new PricingTier());
        return "admin/pricing-manage";
    }

    @PostMapping("/save")
    public String saveTier(@ModelAttribute PricingTier tier,
                           RedirectAttributes redirectAttributes) {
        pricingTierService.saveTier(tier);
        redirectAttributes.addFlashAttribute("success", "Pricing tier saved successfully");
        return "redirect:/admin/pricing";
    }

    @GetMapping("/edit/{id}")
    public String editTier(@PathVariable Long id, Model model) {
        model.addAttribute("tiers", pricingTierService.getAllTiers());
        model.addAttribute("newTier", pricingTierService.getTierById(id));
        return "admin/pricing-manage";
    }

    @PostMapping("/delete/{id}")
    public String deleteTier(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        pricingTierService.deleteTier(id);
        redirectAttributes.addFlashAttribute("success", "Pricing tier deleted");
        return "redirect:/admin/pricing";
    }
}
