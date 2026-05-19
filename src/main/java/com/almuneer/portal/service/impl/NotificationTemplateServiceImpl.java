package com.almuneer.portal.service.impl;

import com.almuneer.portal.model.NotificationTemplate;
import com.almuneer.portal.repository.NotificationTemplateRepository;
import com.almuneer.portal.service.NotificationTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationTemplateServiceImpl implements NotificationTemplateService {

    private final NotificationTemplateRepository templateRepository;

    @Override
    public List<NotificationTemplate> getAll() {
        return templateRepository.findAll();
    }

    @Override
    public NotificationTemplate getById(Long id) {
        return templateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Template not found: " + id));
    }

    @Override
    public NotificationTemplate getByEventName(String eventName) {
        return templateRepository.findByEventName(eventName)
                .orElseThrow(() -> new IllegalArgumentException("Template not found for event: " + eventName));
    }

    @Override
    public NotificationTemplate save(NotificationTemplate template) {
        return templateRepository.save(template);
    }
}
