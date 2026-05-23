package com.almuneer.portal.controller;

import com.almuneer.portal.model.VenueInfo;
import com.almuneer.portal.service.MediaItemService;
import com.almuneer.portal.service.PricingTierService;
import com.almuneer.portal.service.VenueInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final VenueInfoService venueInfoService;
    private final MediaItemService mediaItemService;
    private final PricingTierService pricingTierService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("venue", venueInfoService.getVenueInfo());
        // Show up to 8 gallery preview items
        var allMedia = mediaItemService.getAllMedia();
        model.addAttribute("previewMedia", allMedia.stream().limit(8).toList());
        model.addAttribute("tiers", pricingTierService.getActiveTiers());
        return "visitor/home";
    }

    @GetMapping("/venue")
    public String venueInfo(Model model) {
        model.addAttribute("venue", venueInfoService.getVenueInfo());
        return "visitor/venue";
    }
}

