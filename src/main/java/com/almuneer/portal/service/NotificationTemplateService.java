package com.almuneer.portal.service;

import com.almuneer.portal.model.NotificationTemplate;

import java.util.List;

public interface NotificationTemplateService {
    List<NotificationTemplate> getAll();
    NotificationTemplate getById(Long id);
    NotificationTemplate getByEventName(String eventName);
    NotificationTemplate save(NotificationTemplate template);
}
