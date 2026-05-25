package com.almuneer.portal.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * A simple daily page-visit counter. One row per page per day.
 */
@Entity
@Table(name = "page_visits",
       uniqueConstraints = @UniqueConstraint(columnNames = {"visit_date", "page_path"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PageVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate visitDate;

    @Column(name = "page_path", nullable = false)
    private String pagePath;

    @Column(nullable = false)
    private Long hitCount;
}
