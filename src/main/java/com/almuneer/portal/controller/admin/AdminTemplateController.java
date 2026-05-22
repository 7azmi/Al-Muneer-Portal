package com.almuneer.portal.controller.admin;

import com.almuneer.portal.model.NotificationTemplate;
import com.almuneer.portal.repository.NotificationTemplateRepository;
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
    private final NotificationTemplateRepository templateRepository;

    /** UC016 — list all notification templates */
    @GetMapping
    public String listTemplates(Model model) {
        model.addAttribute("templates", templateService.getAll());
        return "admin/templates-manage";
    }

    /** UC016 — show blank create form */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("templates", templateService.getAll());
        model.addAttribute("editing", new NotificationTemplate());
        model.addAttribute("isNew", true);
        return "admin/templates-manage";
    }

    /** UC016 — edit form for an existing template */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("templates", templateService.getAll());
        model.addAttribute("editing", templateService.getById(id));
        model.addAttribute("isNew", false);
        return "admin/templates-manage";
    }

    /** UC016 — save (create or update) */
    @PostMapping("/save")
    public String save(@ModelAttribute NotificationTemplate template,
                       RedirectAttributes redirectAttributes) {
        templateService.save(template);
        redirectAttributes.addFlashAttribute("success", "Template saved successfully.");
        return "redirect:/admin/templates";
    }

    /** UC016 — delete a template */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        templateRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Template deleted.");
        return "redirect:/admin/templates";
    }
}

