package com.almuneer.portal.controller.admin;

import com.almuneer.portal.service.MediaItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/gallery")
@RequiredArgsConstructor
public class AdminGalleryController {

    private final MediaItemService mediaItemService;

    @GetMapping
    public String manageGallery(Model model) {
        model.addAttribute("mediaItems", mediaItemService.getAllMedia());
        return "admin/gallery-manage";
    }

    @PostMapping("/upload-image")
    public String uploadImage(@RequestParam("file") MultipartFile file,
                              @RequestParam(required = false) String caption,
                              @RequestParam(required = false) String category,
                              RedirectAttributes redirectAttributes) {
        try {
            mediaItemService.addImage(file, caption, category);
            redirectAttributes.addFlashAttribute("success", "Image uploaded successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/gallery";
    }

    @PostMapping("/add-video")
    public String addVideo(@RequestParam String youtubeUrl,
                           @RequestParam(required = false) String caption,
                           @RequestParam(required = false) String category,
                           RedirectAttributes redirectAttributes) {
        try {
            mediaItemService.addYoutubeVideo(youtubeUrl, caption, category);
            redirectAttributes.addFlashAttribute("success", "YouTube video added successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/gallery";
    }

    @PostMapping("/delete/{id}")
    public String deleteMedia(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            mediaItemService.deleteMedia(id);
            redirectAttributes.addFlashAttribute("success", "Media item deleted");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/gallery";
    }
}
