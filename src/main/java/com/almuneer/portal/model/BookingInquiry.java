package com.almuneer.portal.model;

import com.almuneer.portal.model.enums.InquiryStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking_inquiries")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BookingInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;

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
    }
}
