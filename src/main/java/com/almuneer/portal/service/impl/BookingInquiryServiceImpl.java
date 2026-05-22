package com.almuneer.portal.service.impl;

import com.almuneer.portal.model.AvailabilitySlot;
import com.almuneer.portal.model.BookingInquiry;
import com.almuneer.portal.model.enums.InquiryStatus;
import com.almuneer.portal.model.enums.SlotStatus;
import com.almuneer.portal.repository.AvailabilitySlotRepository;
import com.almuneer.portal.repository.BookingInquiryRepository;
import com.almuneer.portal.service.BookingInquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingInquiryServiceImpl implements BookingInquiryService {

    private final BookingInquiryRepository inquiryRepository;
    private final AvailabilitySlotRepository slotRepository;

    @Override
    @Transactional
    public BookingInquiry submitInquiry(BookingInquiry inquiry) {
        BookingInquiry saved = inquiryRepository.save(inquiry);
        AvailabilitySlot slot = saved.getSlot();
        if (slot != null) {
            slot.setStatus(SlotStatus.PENDING);
            slotRepository.save(slot);
        }
        return saved;
    }

    @Override
    public BookingInquiry getById(Long id) {
        return inquiryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inquiry not found: " + id));
    }

    @Override
    public Optional<BookingInquiry> findByReferenceCode(Long referenceCode) {
        return inquiryRepository.findByReferenceCode(referenceCode);
    }

    @Override
    public List<BookingInquiry> getAll() {
        return inquiryRepository.findAllByOrderBySubmissionDateDesc();
    }

    @Override
    public List<BookingInquiry> getFiltered(List<InquiryStatus> excludeStatuses) {
        return inquiryRepository.findByStatusNotInOrderBySubmissionDateDesc(excludeStatuses);
    }

    @Override
    public List<BookingInquiry> getByStatus(InquiryStatus status) {
        return inquiryRepository.findByStatus(status);
    }

    @Override
    @Transactional
    public BookingInquiry updateStatus(Long id, InquiryStatus status, String adminNotes) {
        BookingInquiry inquiry = getById(id);
        inquiry.setStatus(status);
        if (adminNotes != null && !adminNotes.isBlank()) {
            inquiry.setAdminNotes(adminNotes);
        }
        return inquiryRepository.save(inquiry);
    }

    @Override
    @Transactional
    public void markViewed(Long id) {
        BookingInquiry inquiry = getById(id);
        if (inquiry.getStatus() == InquiryStatus.NEW) {
            inquiry.setStatus(InquiryStatus.VIEWED);
            inquiryRepository.save(inquiry);
        }
    }

    @Override
    @Transactional
    public void cancelByVisitor(Long referenceCode) {
        BookingInquiry inquiry = inquiryRepository.findByReferenceCode(referenceCode)
                .orElseThrow(() -> new IllegalArgumentException("Inquiry not found for reference: " + referenceCode));

        // Block cancellation if already cancelled or completed
        if (inquiry.getStatus() == InquiryStatus.CANCELLED_BY_VISITOR
                || inquiry.getStatus() == InquiryStatus.CANCELLED_BY_ADMIN
                || inquiry.getStatus() == InquiryStatus.COMPLETED) {
            throw new IllegalStateException("This inquiry cannot be cancelled.");
        }

        // Block cancellation if a payment proof is attached — admin must handle it
        if (inquiryRepository.hasPaymentProof(inquiry.getInquiryId())) {
            throw new IllegalStateException(
                    "Your inquiry has a payment proof attached. Please contact us via WhatsApp to cancel.");
        }

        // Free the slot back to AVAILABLE
        AvailabilitySlot slot = inquiry.getSlot();
        if (slot != null) {
            slot.setStatus(SlotStatus.AVAILABLE);
            slotRepository.save(slot);
        }

        inquiry.setStatus(InquiryStatus.CANCELLED_BY_VISITOR);
        inquiryRepository.save(inquiry);
    }
}

