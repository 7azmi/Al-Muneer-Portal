package com.almuneer.portal.service;

import com.almuneer.portal.model.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback submit(Feedback feedback);
    List<Feedback> getAll();
    Feedback getById(Long id);
    Feedback markReviewed(Long id, String adminNotes);
}
