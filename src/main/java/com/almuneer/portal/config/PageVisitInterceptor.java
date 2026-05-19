package com.almuneer.portal.config;

import com.almuneer.portal.model.PageVisit;
import com.almuneer.portal.repository.PageVisitRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDate;

/**
 * Intercepts visitor page requests and increments the daily hit counter.
 * Only tracks public visitor paths (not admin or static resources).
 */
@Component
@RequiredArgsConstructor
public class PageVisitInterceptor implements HandlerInterceptor {

    private final PageVisitRepository pageVisitRepository;

    @Override
    @Transactional
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = request.getRequestURI();
        // Only track top-level visitor pages
        if (isTrackable(path)) {
            LocalDate today = LocalDate.now();
            PageVisit visit = pageVisitRepository
                    .findByVisitDateAndPagePath(today, path)
                    .orElse(PageVisit.builder()
                            .visitDate(today)
                            .pagePath(path)
                            .hitCount(0L)
                            .build());
            visit.setHitCount(visit.getHitCount() + 1);
            pageVisitRepository.save(visit);
        }
        return true;
    }

    private boolean isTrackable(String path) {
        return path.equals("/")
                || path.equals("/venue")
                || path.equals("/gallery")
                || path.equals("/calendar")
                || path.equals("/pricing")
                || path.startsWith("/inquiry")
                || path.startsWith("/payment")
                || path.startsWith("/feedback");
    }
}
