package com.almuneer.portal.controller.admin;

import com.almuneer.portal.model.VenueInfo;
import com.almuneer.portal.service.VenueInfoService;
import com.almuneer.portal.util.FaqJsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/venue")
@RequiredArgsConstructor
public class AdminVenueController {

    private final VenueInfoService venueInfoService;

    @GetMapping
    public String editVenue(Model model) {
        VenueInfo venue = venueInfoService.getVenueInfo();
        model.addAttribute("venue", venue);
        model.addAttribute("faqs", FaqJsonUtil.parse(venue.getFaqJson()));
        return "admin/venue-edit";
    }

    @PostMapping
    public String updateVenue(@ModelAttribute VenueInfo venueInfo,
                              @RequestParam(required = false) List<String> faqQuestion,
                              @RequestParam(required = false) List<String> faqAnswer,
                              RedirectAttributes redirectAttributes) {
        venueInfo.setFaqJson(FaqJsonUtil.buildFromPairs(faqQuestion, faqAnswer));
        venueInfoService.updateVenueInfo(venueInfo);
        redirectAttributes.addFlashAttribute("success", "Venue information updated successfully");
        return "redirect:/admin/venue";
    }
}
