package com.almuneer.portal.controller;

import com.almuneer.portal.model.VenueInfo;
import com.almuneer.portal.service.VenueInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final VenueInfoService venueInfoService;

    @GetMapping("/")
    public String home(Model model) {
        VenueInfo venue = venueInfoService.getVenueInfo();
        model.addAttribute("venue", venue);
        return "visitor/home";
    }

    @GetMapping("/venue")
    public String venueInfo(Model model) {
        VenueInfo venue = venueInfoService.getVenueInfo();
        model.addAttribute("venue", venue);
        return "visitor/venue";
    }
}
