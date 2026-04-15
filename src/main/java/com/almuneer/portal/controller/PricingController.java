package com.almuneer.portal.controller;

import com.almuneer.portal.model.PricingTier;
import com.almuneer.portal.service.PricingTierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PricingController {

    private final PricingTierService pricingTierService;

    @GetMapping("/pricing")
    public String pricing(Model model) {
        List<PricingTier> tiers = pricingTierService.getActiveTiers();
        model.addAttribute("tiers", tiers);
        return "visitor/pricing";
    }
}
