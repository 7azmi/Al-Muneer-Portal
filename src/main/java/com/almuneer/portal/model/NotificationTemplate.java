package com.almuneer.portal.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification_templates")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NotificationTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long templateId;

    /** A short machine-readable name, e.g. "INQUIRY_RECEIVED", "PAYMENT_VERIFIED" */
    @Column(nullable = false, unique = true, length = 50)
    private String eventName;

    /** Human-readable label shown in the admin UI */
    @Column(nullable = false)
    private String label;

    /**
     * The WhatsApp message body. Supports placeholders:
     * {visitorName}, {inquiryId}, {eventDate}, {status}
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String templateText;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
