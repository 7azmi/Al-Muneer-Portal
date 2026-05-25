package com.almuneer.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GalleryController {

    /** Full gallery lives on the home page */
    @GetMapping("/gallery")
    public String gallery(@RequestParam(defaultValue = "all") String category) {
        if ("all".equalsIgnoreCase(category)) {
            return "redirect:/#gallery";
        }
        return "redirect:/#gallery?category=" + category;
    }
}
