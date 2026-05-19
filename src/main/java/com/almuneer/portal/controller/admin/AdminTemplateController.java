package com.almuneer.portal.controller.admin;

import com.almuneer.portal.model.NotificationTemplate;
import com.almuneer.portal.service.NotificationTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/templates")
@RequiredArgsConstructor
public class AdminTemplateController {

    private final NotificationTemplateService templateService;

    /** UC016 — list all notification templates */
    @GetMapping
    public String listTemplates(Model model) {
        List<NotificationTemplate> templates = templateService.getAll();
        model.addAttribute("templates", templates);
        return "admin/templates-manage";
    }

    /** UC016 — edit form for a single template */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        List<NotificationTemplate> templates = templateService.getAll();
        model.addAttribute("templates", templates);
        model.addAttribute("editing", templateService.getById(id));
        return "admin/templates-manage";
    }

    /** UC016 — save updated template */
    @PostMapping("/save")
    public String save(@ModelAttribute NotificationTemplate template,
                       RedirectAttributes redirectAttributes) {
        templateService.save(template);
        redirectAttributes.addFlashAttribute("success", "Template saved successfully.");
        return "redirect:/admin/templates";
    }
}
