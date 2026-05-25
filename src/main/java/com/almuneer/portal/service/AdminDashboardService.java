package com.almuneer.portal.service;

import com.almuneer.portal.model.BookingInquiry;
import com.almuneer.portal.model.enums.InquiryStatus;
import com.almuneer.portal.model.enums.VerificationStatus;
import com.almuneer.portal.repository.BookingInquiryRepository;
import com.almuneer.portal.repository.FeedbackRepository;
import com.almuneer.portal.repository.PageVisitRepository;
import com.almuneer.portal.repository.PaymentProofRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private final BookingInquiryRepository inquiryRepository;
    private final PaymentProofRepository paymentProofRepository;
    private final FeedbackRepository feedbackRepository;
    private final PageVisitRepository pageVisitRepository;

    public record DashboardStats(
            long newInquiries,
            long activeInquiries,
            long pendingPayments,
            long unreviewedFeedback,
            long visitsLast7Days,
            long visitsToday,
            double averageRating,
            List<BookingInquiry> recentInquiries,
            Map<String, Long> topPages
    ) {}

    public DashboardStats getStats() {
        long newInquiries = inquiryRepository.countByStatus(InquiryStatus.NEW);
        long activeInquiries = inquiryRepository.countByStatusNotIn(
                List.of(InquiryStatus.COMPLETED, InquiryStatus.CANCELLED_BY_ADMIN, InquiryStatus.CANCELLED_BY_VISITOR));

        long pendingPayments = paymentProofRepository.countByVerificationStatus(
                VerificationStatus.PENDING_VERIFICATION);

        long unreviewedFeedback = feedbackRepository.countByIsReviewed(false);

        LocalDate today = LocalDate.now();
        long visitsToday = sumHitsForDay(today);
        long visitsLast7Days = sumHitsSince(today.minusDays(6));

        double avgRating = feedbackRepository.findAverageRating();

        List<BookingInquiry> recent = inquiryRepository.findAllByOrderBySubmissionDateDesc()
                .stream().limit(5).toList();

        Map<String, Long> topPages = new LinkedHashMap<>();
        pageVisitRepository.findTotalHitsPerPage().stream().limit(5).forEach(row ->
                topPages.put((String) row[0], ((Number) row[1]).longValue()));

        return new DashboardStats(
                newInquiries,
                activeInquiries,
                pendingPayments,
                unreviewedFeedback,
                visitsLast7Days,
                visitsToday,
                avgRating,
                recent,
                topPages
        );
    }

    private long sumHitsForDay(LocalDate day) {
        return pageVisitRepository.findDailyHitsSince(day).stream()
                .filter(row -> day.equals(row[0]))
                .mapToLong(row -> ((Number) row[1]).longValue())
                .findFirst().orElse(0L);
    }

    private long sumHitsSince(LocalDate since) {
        return pageVisitRepository.findDailyHitsSince(since).stream()
                .mapToLong(row -> ((Number) row[1]).longValue())
                .sum();
    }
}
