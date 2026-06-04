package com.almuneer.portal.repository;

import com.almuneer.portal.model.PaymentProof;
import com.almuneer.portal.model.enums.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentProofRepository extends JpaRepository<PaymentProof, Long> {
    List<PaymentProof> findByInquiry_InquiryId(Long inquiryId);
    long countByVerificationStatus(VerificationStatus status);
    long countByVerificationStatusAndUploadTimestampBetween(VerificationStatus status, LocalDateTime from, LocalDateTime to);
}

