package com.almuneer.portal.repository;

import com.almuneer.portal.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    long countByIsReviewed(boolean reviewed);
    List<Feedback> findAllByOrderBySubmissionDateDesc();
    @Query("SELECT COALESCE(AVG(f.rating), 0) FROM Feedback f")
    double findAverageRating();

    long countByRating(Integer rating);
    long countByRatingAndSubmissionDateBetween(Integer rating, LocalDateTime from, LocalDateTime to);
}

