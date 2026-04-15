package com.almuneer.portal.controller;

import com.almuneer.portal.service.AvailabilitySlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CalendarController {

    private final AvailabilitySlotService slotService;

    @GetMapping("/calendar")
    public String calendar(Model model) {
        LocalDate now = LocalDate.now();
        model.addAttribute("currentYear", now.getYear());
        model.addAttribute("currentMonth", now.getMonthValue());
        return "visitor/calendar";
    }

    @GetMapping(value = "/api/calendar/slots", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getSlots(
            @RequestParam int year, @RequestParam int month) {
        Map<String, String> slots = slotService.getSlotStatusMap(year, month);
        return ResponseEntity.ok(slots);
    }
}
