package com.almuneer.portal.controller;

import com.almuneer.portal.model.VenueInfo;
import com.almuneer.portal.service.MediaItemService;
import com.almuneer.portal.service.PricingTierService;
import com.almuneer.portal.service.VenueInfoService;
import com.almuneer.portal.util.FaqJsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final VenueInfoService venueInfoService;
    private final MediaItemService mediaItemService;
    private final PricingTierService pricingTierService;

    @GetMapping("/")
    public String home(Model model) {
        VenueInfo venue = venueInfoService.getVenueInfo();
        model.addAttribute("venue", venue);
        model.addAttribute("faqs", FaqJsonUtil.parse(venue.getFaqJson()));
        model.addAttribute("mediaItems", mediaItemService.getAllMedia());
        model.addAttribute("tiers", pricingTierService.getActiveTiers());
        LocalDate now = LocalDate.now();
        model.addAttribute("currentYear", now.getYear());
        model.addAttribute("currentMonth", now.getMonthValue());
        return "visitor/home";
    }

    /** Legacy /venue URL → home venue section */
    @GetMapping("/venue")
    public String venueInfo() {
        return "redirect:/#venue";
    }

    /** Legacy /faq URL → home FAQ section */
    @GetMapping("/faq")
    public String faq() {
        return "redirect:/#faq";
    }
}

