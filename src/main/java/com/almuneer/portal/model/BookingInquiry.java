package com.almuneer.portal.model;

import com.almuneer.portal.model.enums.InquiryStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "booking_inquiries")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BookingInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;

    /**
     * Visitor-facing 9-digit reference code (e.g. 472918305).
     * Random, unique — used for lookup and cookie storage.
     * Nullable only to allow Hibernate schema-update on existing tables;
     * @PrePersist always populates this before insert.
     */
    @Column(unique = true)
    private Long referenceCode;

    @Column(nullable = false)
    private String visitorName;

    @Column(nullable = false)
    private String visitorWhatsApp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_id", nullable = false)
    private AvailabilitySlot slot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pricing_id")
    private PricingTier pricingTier;

    private Integer numGuests;

    private String eventType;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    private LocalDateTime submissionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private InquiryStatus status;

    @Column(columnDefinition = "TEXT")
    private String adminNotes;

    @PrePersist
    protected void onCreate() {
        this.submissionDate = LocalDateTime.now();
        if (this.status == null) {
            this.status = InquiryStatus.NEW;
        }
        if (this.referenceCode == null) {
            // 9-digit random: 100_000_000 – 999_999_999
            this.referenceCode = ThreadLocalRandom.current().nextLong(100_000_000L, 1_000_000_000L);
        }
    }
}

