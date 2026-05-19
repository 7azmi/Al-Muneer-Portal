package com.almuneer.portal.util;

import com.almuneer.portal.model.enums.InquiryStatus;
import com.almuneer.portal.repository.BookingInquiryRepository;
import com.almuneer.portal.repository.FeedbackRepository;
import com.almuneer.portal.repository.PaymentProofRepository;
import com.almuneer.portal.model.enums.VerificationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Generates predefined statistical summaries for the admin reports page.
 */
@Component
@RequiredArgsConstructor
public class ReportGeneratorUtil {

    private final BookingInquiryRepository inquiryRepository;
    private final PaymentProofRepository proofRepository;
    private final FeedbackRepository feedbackRepository;

    public Map<String, Object> generateSummary() {
        Map<String, Object> report = new LinkedHashMap<>();

        // Inquiry volume
        report.put("totalInquiries", inquiryRepository.count());
        report.put("newInquiries", inquiryRepository.countByStatus(InquiryStatus.NEW));
        report.put("confirmedBookings", inquiryRepository.countByStatus(InquiryStatus.CONFIRMED));
        report.put("completedEvents", inquiryRepository.countByStatus(InquiryStatus.COMPLETED));
        report.put("cancelledInquiries",
                inquiryRepository.countByStatus(InquiryStatus.CANCELLED_BY_ADMIN)
                + inquiryRepository.countByStatus(InquiryStatus.CANCELLED_BY_VISITOR));

        // Payment proofs
        report.put("proofsPending", proofRepository.countByVerificationStatus(VerificationStatus.PENDING_VERIFICATION));
        report.put("proofsVerified", proofRepository.countByVerificationStatus(VerificationStatus.VERIFIED));
        report.put("proofsRejected", proofRepository.countByVerificationStatus(VerificationStatus.REJECTED));

        // Feedback
        report.put("totalFeedback", feedbackRepository.count());
        report.put("unreviewed", feedbackRepository.countByIsReviewed(false));
        report.put("averageRating", Math.round(feedbackRepository.findAverageRating() * 10.0) / 10.0);

        return report;
    }
}
