package com.almuneer.portal.controller.admin;

import com.almuneer.portal.repository.PageVisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/analytics")
@RequiredArgsConstructor
public class AdminAnalyticsController {

    private final PageVisitRepository pageVisitRepository;

    @GetMapping
    public String analytics(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            Model model) {

        Long totalHits;
        List<Object[]> perPage;
        List<Object[]> daily;

        if (fromDate != null && toDate != null) {
            totalHits = pageVisitRepository.sumAllHitsBetween(fromDate, toDate);
            perPage = pageVisitRepository.findTotalHitsPerPageBetween(fromDate, toDate);
            daily = pageVisitRepository.findDailyHitsBetween(fromDate, toDate);
        } else {
            totalHits = pageVisitRepository.sumAllHits();
            perPage = pageVisitRepository.findTotalHitsPerPage();
            daily = pageVisitRepository.findDailyHitsSince(LocalDate.now().minusDays(29));
        }

        model.addAttribute("totalHits", totalHits != null ? totalHits : 0L);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);

        // Page-by-page totals
        Map<String, Long> pageMap = new LinkedHashMap<>();
        for (Object[] row : perPage) {
            pageMap.put((String) row[0], ((Number) row[1]).longValue());
        }
        model.addAttribute("pageHits", pageMap);

        // Daily totals
        Map<String, Long> dailyMap = new LinkedHashMap<>();
        for (Object[] row : daily) {
            dailyMap.put(row[0].toString(), ((Number) row[1]).longValue());
        }
        model.addAttribute("dailyHits", dailyMap);

        return "admin/analytics";
    }
}
