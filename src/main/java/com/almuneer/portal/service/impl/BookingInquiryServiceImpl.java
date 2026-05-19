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

@Service
@RequiredArgsConstructor
public class BookingInquiryServiceImpl implements BookingInquiryService {

    private final BookingInquiryRepository inquiryRepository;
    private final AvailabilitySlotRepository slotRepository;

    @Override
    @Transactional
    public BookingInquiry submitInquiry(BookingInquiry inquiry) {
        // Persist the inquiry first
        BookingInquiry saved = inquiryRepository.save(inquiry);
        // Set the linked slot to PENDING
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
    public List<BookingInquiry> getAll() {
        return inquiryRepository.findAll();
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
}
