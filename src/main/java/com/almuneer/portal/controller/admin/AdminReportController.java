package com.almuneer.portal.controller.admin;

import com.almuneer.portal.util.ReportGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/reports")
@RequiredArgsConstructor
public class AdminReportController {

    private final ReportGeneratorUtil reportGenerator;

    /** UC014 — generate and display reports */
    @GetMapping
    public String reports(Model model) {
        model.addAttribute("report", reportGenerator.generateSummary());
        return "admin/reports";
    }
}
