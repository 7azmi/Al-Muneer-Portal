package com.almuneer.portal.model;

import com.almuneer.portal.model.enums.VerificationStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_proofs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PaymentProof {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proofId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id", nullable = false)
    private BookingInquiry inquiry;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private LocalDateTime uploadTimestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 25)
    private VerificationStatus verificationStatus;

    @Column(columnDefinition = "TEXT")
    private String adminNotes;

    @PrePersist
    protected void onCreate() {
        this.uploadTimestamp = LocalDateTime.now();
        if (this.verificationStatus == null) {
            this.verificationStatus = VerificationStatus.PENDING_VERIFICATION;
        }
    }
}
