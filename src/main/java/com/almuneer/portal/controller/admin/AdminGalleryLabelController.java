package com.almuneer.portal.controller.admin;

import com.almuneer.portal.model.GalleryLabel;
import com.almuneer.portal.service.GalleryLabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/gallery/labels")
@RequiredArgsConstructor
public class AdminGalleryLabelController {

    private final GalleryLabelService labelService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("labels", labelService.getAll());
        return "admin/gallery-labels";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam String name,
            @RequestParam(required = false) String icon,
            @RequestParam(required = false, defaultValue = "0") Integer sortOrder,
            RedirectAttributes ra) {
        try {
            labelService.create(name, icon, sortOrder);
            ra.addFlashAttribute("success", "Label '" + name + "' created.");
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/gallery/labels";
    }

    @PostMapping("/{id}/update")
    public String update(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam(required = false) String icon,
            @RequestParam(required = false, defaultValue = "0") Integer sortOrder,
            RedirectAttributes ra) {
        try {
            labelService.update(id, name, icon, sortOrder);
            ra.addFlashAttribute("success", "Label updated.");
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/gallery/labels";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        labelService.delete(id);
        ra.addFlashAttribute("success", "Label deleted.");
        return "redirect:/admin/gallery/labels";
    }
}
