package com.almuneer.portal.service;

import com.almuneer.portal.model.BookingInquiry;
import com.almuneer.portal.model.enums.InquiryStatus;

import java.util.List;
import java.util.Optional;

public interface BookingInquiryService {
    BookingInquiry submitInquiry(BookingInquiry inquiry);
    BookingInquiry getById(Long id);
    Optional<BookingInquiry> findByReferenceCode(Long referenceCode);
    List<BookingInquiry> getAll();
    List<BookingInquiry> getFiltered(List<InquiryStatus> excludeStatuses);
    List<BookingInquiry> getByStatus(InquiryStatus status);
    BookingInquiry updateStatus(Long id, InquiryStatus status, String adminNotes);
    void markViewed(Long id);
    /** Visitor self-cancel — only allowed when no payment proof is attached */
    void cancelByVisitor(Long referenceCode);
}

