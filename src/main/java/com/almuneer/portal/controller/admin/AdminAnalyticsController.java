package com.almuneer.portal.controller.admin;

import com.almuneer.portal.repository.PageVisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/analytics")
@RequiredArgsConstructor
public class AdminAnalyticsController {

    private final PageVisitRepository pageVisitRepository;

    /** UC010 — traffic analytics overview */
    @GetMapping
    public String analytics(Model model) {
        Long totalHits = pageVisitRepository.sumAllHits();
        model.addAttribute("totalHits", totalHits != null ? totalHits : 0L);

        // Page-by-page totals
        List<Object[]> perPage = pageVisitRepository.findTotalHitsPerPage();
        Map<String, Long> pageMap = new LinkedHashMap<>();
        for (Object[] row : perPage) {
            pageMap.put((String) row[0], ((Number) row[1]).longValue());
        }
        model.addAttribute("pageHits", pageMap);

        // Last 30 days daily totals
        List<Object[]> daily = pageVisitRepository.findDailyHitsSince(LocalDate.now().minusDays(29));
        Map<String, Long> dailyMap = new LinkedHashMap<>();
        for (Object[] row : daily) {
            dailyMap.put(row[0].toString(), ((Number) row[1]).longValue());
        }
        model.addAttribute("dailyHits", dailyMap);

        return "admin/analytics";
    }
}
