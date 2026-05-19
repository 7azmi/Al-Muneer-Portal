package com.almuneer.portal.repository;

import com.almuneer.portal.model.BookingInquiry;
import com.almuneer.portal.model.enums.InquiryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingInquiryRepository extends JpaRepository<BookingInquiry, Long> {
    List<BookingInquiry> findByStatus(InquiryStatus status);
    List<BookingInquiry> findAllByOrderBySubmissionDateDesc();
    long countByStatus(InquiryStatus status);
}
