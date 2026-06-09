package com.almuneer.portal.util;

import com.almuneer.portal.model.enums.InquiryStatus;
import com.almuneer.portal.repository.BookingInquiryRepository;
import com.almuneer.portal.repository.FeedbackRepository;
import com.almuneer.portal.repository.PaymentProofRepository;
import com.almuneer.portal.model.enums.VerificationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ReportGeneratorUtil {

    private final BookingInquiryRepository inquiryRepository;
    private final PaymentProofRepository proofRepository;
    private final FeedbackRepository feedbackRepository;

    public Map<String, Object> generateSummary() {
        return generateSummary(null, null);
    }

    public Map<String, Object> generateSummary(LocalDate fromDate, LocalDate toDate) {
        Map<String, Object> report = new LinkedHashMap<>();

        LocalDateTime from = null;
        LocalDateTime to = null;

        if (fromDate != null) {
            from = fromDate.atStartOfDay();
        }
        if (toDate != null) {
            to = toDate.atTime(LocalTime.MAX);
        }

        // Inquiry volume
        if (from != null && to != null) {
            report.put("totalInquiries", countInquiriesBetween(from, to));
            report.put("newInquiries", inquiryRepository.countByStatusAndSubmissionDateBetween(InquiryStatus.NEW, from, to));
            report.put("confirmedBookings", inquiryRepository.countByStatusAndSubmissionDateBetween(InquiryStatus.CONFIRMED, from, to));
            report.put("completedEvents", inquiryRepository.countByStatusAndSubmissionDateBetween(InquiryStatus.COMPLETED, from, to));
            report.put("cancelledInquiries",
                    inquiryRepository.countByStatusAndSubmissionDateBetween(InquiryStatus.CANCELLED_BY_ADMIN, from, to)
                    + inquiryRepository.countByStatusAndSubmissionDateBetween(InquiryStatus.CANCELLED_BY_VISITOR, from, to));
        } else {
            report.put("totalInquiries", inquiryRepository.count());
            report.put("newInquiries", inquiryRepository.countByStatus(InquiryStatus.NEW));
            report.put("confirmedBookings", inquiryRepository.countByStatus(InquiryStatus.CONFIRMED));
            report.put("completedEvents", inquiryRepository.countByStatus(InquiryStatus.COMPLETED));
            report.put("cancelledInquiries",
                    inquiryRepository.countByStatus(InquiryStatus.CANCELLED_BY_ADMIN)
                    + inquiryRepository.countByStatus(InquiryStatus.CANCELLED_BY_VISITOR));
        }

        // Payment proofs
        if (from != null && to != null) {
            report.put("proofsPending", proofRepository.countByVerificationStatusAndUploadTimestampBetween(VerificationStatus.PENDING_VERIFICATION, from, to));
            report.put("proofsVerified", proofRepository.countByVerificationStatusAndUploadTimestampBetween(VerificationStatus.VERIFIED, from, to));
            report.put("proofsRejected", proofRepository.countByVerificationStatusAndUploadTimestampBetween(VerificationStatus.REJECTED, from, to));
        } else {
            report.put("proofsPending", proofRepository.countByVerificationStatus(VerificationStatus.PENDING_VERIFICATION));
            report.put("proofsVerified", proofRepository.countByVerificationStatus(VerificationStatus.VERIFIED));
            report.put("proofsRejected", proofRepository.countByVerificationStatus(VerificationStatus.REJECTED));
        }

        // Feedback
        if (from != null && to != null) {
            report.put("totalFeedback", countFeedbackBetween(from, to));
            report.put("unreviewed", countUnreviewedFeedback(from, to));
            report.put("averageRating", calculateAverageRating(from, to));
            report.put("feedbackRatings", getFeedbackRatingDistribution(from, to));
        } else {
            report.put("totalFeedback", feedbackRepository.count());
            report.put("unreviewed", feedbackRepository.countByIsReviewed(false));
            report.put("averageRating", Math.round(feedbackRepository.findAverageRating() * 10.0) / 10.0);
            report.put("feedbackRatings", getFeedbackRatingDistribution(null, null));
        }

        // Inquiry status breakdown for chart
        List<Map<String, Object>> inquiryStatusData = new ArrayList<>();
        for (InquiryStatus status : InquiryStatus.values()) {
            long count = (from != null && to != null)
                ? inquiryRepository.countByStatusAndSubmissionDateBetween(status, from, to)
                : inquiryRepository.countByStatus(status);
            if (count > 0) {
                Map<String, Object> item = new LinkedHashMap<>();
                item.put("status", status.name());
                item.put("count", count);
                inquiryStatusData.add(item);
            }
        }
        report.put("inquiryStatusBreakdown", inquiryStatusData);

        // Payment status breakdown for chart
        List<Map<String, Object>> paymentStatusData = new ArrayList<>();
        for (VerificationStatus status : VerificationStatus.values()) {
            long count = (from != null && to != null)
                ? proofRepository.countByVerificationStatusAndUploadTimestampBetween(status, from, to)
                : proofRepository.countByVerificationStatus(status);
            if (count > 0) {
                Map<String, Object> item = new LinkedHashMap<>();
                item.put("status", status.name());
                item.put("count", count);
                paymentStatusData.add(item);
            }
        }
        report.put("paymentStatusBreakdown", paymentStatusData);

        return report;
    }

    private long countInquiriesBetween(LocalDateTime from, LocalDateTime to) {
        long count = 0;
        for (InquiryStatus status : InquiryStatus.values()) {
            count += inquiryRepository.countByStatusAndSubmissionDateBetween(status, from, to);
        }
        return count;
    }

    private long countFeedbackBetween(LocalDateTime from, LocalDateTime to) {
        long count = 0;
        for (int rating = 1; rating <= 5; rating++) {
            count += feedbackRepository.countByRatingAndSubmissionDateBetween(rating, from, to);
        }
        return count;
    }

    private long countUnreviewedFeedback(LocalDateTime from, LocalDateTime to) {
        return feedbackRepository.count() - countFeedbackBetween(from, to);
    }

    private double calculateAverageRating(LocalDateTime from, LocalDateTime to) {
        double total = 0;
        long count = 0;
        for (int rating = 1; rating <= 5; rating++) {
            long ratingCount = feedbackRepository.countByRatingAndSubmissionDateBetween(rating, from, to);
            total += rating * ratingCount;
            count += ratingCount;
        }
        if (count == 0) return 0;
        return Math.round((total / count) * 10.0) / 10.0;
    }

    private List<Map<String, Object>> getFeedbackRatingDistribution(LocalDateTime from, LocalDateTime to) {
        List<Map<String, Object>> ratings = new ArrayList<>();
        for (int rating = 5; rating >= 1; rating--) {
            long count = (from != null && to != null)
                ? feedbackRepository.countByRatingAndSubmissionDateBetween(rating, from, to)
                : feedbackRepository.countByRating(rating);
            if (count > 0) {
                Map<String, Object> item = new LinkedHashMap<>();
                item.put("rating", rating);
                item.put("count", count);
                ratings.add(item);
            }
        }
        return ratings;
    }
}
