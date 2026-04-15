package com.almuneer.portal.controller;

import com.almuneer.portal.model.MediaItem;
import com.almuneer.portal.service.MediaItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GalleryController {

    private final MediaItemService mediaItemService;

    @GetMapping("/gallery")
    public String gallery(@RequestParam(defaultValue = "all") String category, Model model) {
        List<MediaItem> items = mediaItemService.getMediaByCategory(category);
        model.addAttribute("mediaItems", items);
        model.addAttribute("activeCategory", category);
        return "visitor/gallery";
    }
}
