package com.almuneer.portal.repository;

import com.almuneer.portal.model.BookingInquiry;
import com.almuneer.portal.model.enums.InquiryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookingInquiryRepository extends JpaRepository<BookingInquiry, Long> {
    List<BookingInquiry> findByStatus(InquiryStatus status);
    List<BookingInquiry> findAllByOrderBySubmissionDateDesc();
    List<BookingInquiry> findByVisitorWhatsAppOrderBySubmissionDateDesc(String whatsApp);
    List<BookingInquiry> findByStatusNotInOrderBySubmissionDateDesc(List<InquiryStatus> statuses);
    Optional<BookingInquiry> findByReferenceCode(Long referenceCode);
    long countByStatus(InquiryStatus status);
    long countByStatusNotIn(List<InquiryStatus> statuses);

    /** Check whether any payment proof exists for an inquiry */
    @Query("SELECT COUNT(p) > 0 FROM PaymentProof p WHERE p.inquiry.inquiryId = :inquiryId")
    boolean hasPaymentProof(Long inquiryId);
}

