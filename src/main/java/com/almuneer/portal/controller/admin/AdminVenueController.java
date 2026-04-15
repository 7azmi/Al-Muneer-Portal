package com.almuneer.portal.controller.admin;

import com.almuneer.portal.model.VenueInfo;
import com.almuneer.portal.service.VenueInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/venue")
@RequiredArgsConstructor
public class AdminVenueController {

    private final VenueInfoService venueInfoService;

    @GetMapping
    public String editVenue(Model model) {
        model.addAttribute("venue", venueInfoService.getVenueInfo());
        return "admin/venue-edit";
    }

    @PostMapping
    public String updateVenue(@ModelAttribute VenueInfo venueInfo,
                              RedirectAttributes redirectAttributes) {
        venueInfoService.updateVenueInfo(venueInfo);
        redirectAttributes.addFlashAttribute("success", "Venue information updated successfully");
        return "redirect:/admin/venue";
    }
}
