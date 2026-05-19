package com.almuneer.portal.service;

import com.almuneer.portal.model.BookingInquiry;
import com.almuneer.portal.model.enums.InquiryStatus;

import java.util.List;

public interface BookingInquiryService {
    BookingInquiry submitInquiry(BookingInquiry inquiry);
    BookingInquiry getById(Long id);
    List<BookingInquiry> getAll();
    List<BookingInquiry> getByStatus(InquiryStatus status);
    BookingInquiry updateStatus(Long id, InquiryStatus status, String adminNotes);
    void markViewed(Long id);
}
