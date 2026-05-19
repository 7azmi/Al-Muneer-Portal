package com.almuneer.portal.service.impl;

import com.almuneer.portal.model.BookingInquiry;
import com.almuneer.portal.model.PaymentProof;
import com.almuneer.portal.model.enums.InquiryStatus;
import com.almuneer.portal.model.enums.SlotStatus;
import com.almuneer.portal.model.enums.VerificationStatus;
import com.almuneer.portal.repository.AvailabilitySlotRepository;
import com.almuneer.portal.repository.BookingInquiryRepository;
import com.almuneer.portal.repository.PaymentProofRepository;
import com.almuneer.portal.service.PaymentProofService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentProofServiceImpl implements PaymentProofService {

    private final PaymentProofRepository proofRepository;
    private final BookingInquiryRepository inquiryRepository;
    private final AvailabilitySlotRepository slotRepository;

    @Override
    @Transactional
    public PaymentProof submitProof(Long inquiryId, String fileName, String filePath) {
        BookingInquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new IllegalArgumentException("Inquiry not found: " + inquiryId));
        PaymentProof proof = PaymentProof.builder()
                .inquiry(inquiry)
                .fileName(fileName)
                .filePath(filePath)
                .verificationStatus(VerificationStatus.PENDING_VERIFICATION)
                .build();
        return proofRepository.save(proof);
    }

    @Override
    public PaymentProof getById(Long id) {
        return proofRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment proof not found: " + id));
    }

    @Override
    public List<PaymentProof> getAll() {
        return proofRepository.findAll();
    }

    @Override
    public List<PaymentProof> getByInquiryId(Long inquiryId) {
        return proofRepository.findByInquiry_InquiryId(inquiryId);
    }

    @Override
    @Transactional
    public PaymentProof updateStatus(Long proofId, VerificationStatus status, String adminNotes) {
        PaymentProof proof = getById(proofId);
        proof.setVerificationStatus(status);
        if (adminNotes != null && !adminNotes.isBlank()) {
            proof.setAdminNotes(adminNotes);
        }
        PaymentProof saved = proofRepository.save(proof);

        // Cascade: if Verified → Inquiry=CONFIRMED, Slot=BOOKED
        if (status == VerificationStatus.VERIFIED) {
            BookingInquiry inquiry = saved.getInquiry();
            inquiry.setStatus(InquiryStatus.CONFIRMED);
            inquiryRepository.save(inquiry);

            if (inquiry.getSlot() != null) {
                inquiry.getSlot().setStatus(SlotStatus.BOOKED);
                slotRepository.save(inquiry.getSlot());
            }
        }

        return saved;
    }
}
