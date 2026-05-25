package com.almuneer.portal.service.impl;

import com.almuneer.portal.model.Feedback;
import com.almuneer.portal.repository.FeedbackRepository;
import com.almuneer.portal.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Override
    public Feedback submit(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Feedback not found: " + id));
    }

    @Override
    @Transactional
    public Feedback markReviewed(Long id, String adminNotes) {
        Feedback feedback = getById(id);
        feedback.setIsReviewed(true);
        if (adminNotes != null && !adminNotes.isBlank()) {
            feedback.setAdminNotes(adminNotes);
        }
        return feedbackRepository.save(feedback);
    }
}
