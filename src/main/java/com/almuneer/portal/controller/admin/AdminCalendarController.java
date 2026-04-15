package com.almuneer.portal.controller.admin;

import com.almuneer.portal.model.enums.SlotStatus;
import com.almuneer.portal.service.AvailabilitySlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/admin/calendar")
@RequiredArgsConstructor
public class AdminCalendarController {

    private final AvailabilitySlotService slotService;

    @GetMapping
    public String manageCalendar(Model model) {
        LocalDate now = LocalDate.now();
        model.addAttribute("currentYear", now.getYear());
        model.addAttribute("currentMonth", now.getMonthValue());
        return "admin/calendar-manage";
    }

    @PostMapping("/set-status")
    @ResponseBody
    public ResponseEntity<Map<String, String>> setSlotStatus(
            @RequestParam String date,
            @RequestParam String status,
            @RequestParam(required = false) String notes) {
        slotService.setSlotStatus(
                LocalDate.parse(date),
                SlotStatus.valueOf(status),
                notes
        );
        return ResponseEntity.ok(Map.of("status", "success"));
    }
}
