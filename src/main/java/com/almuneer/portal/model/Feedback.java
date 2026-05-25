package com.almuneer.portal.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedbacks")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @Column(nullable = true)
    private String visitorName;

    @Column(nullable = true)
    private String visitorWhatsApp;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String feedbackText;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private LocalDateTime submissionDate;

    @Column(nullable = false)
    private Boolean isReviewed = false;

    @Column(columnDefinition = "TEXT")
    private String adminNotes;

    @PrePersist
    protected void onCreate() {
        this.submissionDate = LocalDateTime.now();
    }
}
