package org.example.notificationrebalancer.service;

import org.example.notificationrebalancer.db.entity.NotificationSession;
import org.example.notificationrebalancer.db.repository.NotificationSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class NotificationSessionService {
    private final NotificationSessionRepository notificationSessionRepository;

    @Autowired
    public NotificationSessionService(NotificationSessionRepository notificationSessionRepository) {
        this.notificationSessionRepository = notificationSessionRepository;
    }

    public NotificationSession getNotificationSessionById(Long id) throws NoSuchElementException {
        return notificationSessionRepository.findById(id).orElseThrow();
    }
}
