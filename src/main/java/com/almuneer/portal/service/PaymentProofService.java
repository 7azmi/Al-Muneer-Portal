package com.almuneer.portal.service;

import com.almuneer.portal.model.PaymentProof;
import com.almuneer.portal.model.enums.VerificationStatus;

import java.util.List;

public interface PaymentProofService {
    PaymentProof submitProof(Long inquiryId, String fileName, String filePath);
    PaymentProof getById(Long id);
    List<PaymentProof> getAll();
    List<PaymentProof> getByInquiryId(Long inquiryId);
    /** Verify or reject a proof; cascades state when Verified */
    PaymentProof updateStatus(Long proofId, VerificationStatus status, String adminNotes);
}
