package com.almuneer.portal.repository;

import com.almuneer.portal.model.PageVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PageVisitRepository extends JpaRepository<PageVisit, Long> {
    Optional<PageVisit> findByVisitDateAndPagePath(LocalDate date, String pagePath);

    /** Totals per page path across all dates, for the analytics dashboard */
    @Query("SELECT p.pagePath, SUM(p.hitCount) FROM PageVisit p GROUP BY p.pagePath ORDER BY SUM(p.hitCount) DESC")
    List<Object[]> findTotalHitsPerPage();

    /** Totals per day, for the last N days chart */
    @Query("SELECT p.visitDate, SUM(p.hitCount) FROM PageVisit p WHERE p.visitDate >= :since GROUP BY p.visitDate ORDER BY p.visitDate")
    List<Object[]> findDailyHitsSince(LocalDate since);

    @Query("SELECT SUM(p.hitCount) FROM PageVisit p")
    Long sumAllHits();

    @Query("SELECT SUM(p.hitCount) FROM PageVisit p WHERE p.visitDate >= :from AND p.visitDate <= :to")
    Long sumAllHitsBetween(LocalDate from, LocalDate to);

    @Query("SELECT p.pagePath, SUM(p.hitCount) FROM PageVisit p WHERE p.visitDate >= :from AND p.visitDate <= :to GROUP BY p.pagePath ORDER BY SUM(p.hitCount) DESC")
    List<Object[]> findTotalHitsPerPageBetween(LocalDate from, LocalDate to);

    @Query("SELECT p.visitDate, SUM(p.hitCount) FROM PageVisit p WHERE p.visitDate >= :from AND p.visitDate <= :to GROUP BY p.visitDate ORDER BY p.visitDate")
    List<Object[]> findDailyHitsBetween(LocalDate from, LocalDate to);
}
