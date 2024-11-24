package org.example.notificationrebalancer.service;

import org.example.notificationrebalancer.Enums.NotificationStatus;
import org.example.notificationrebalancer.db.entity.Notification;
import org.example.notificationrebalancer.db.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getNotificationsByStatus(NotificationStatus notificationStatus) {
        return notificationRepository.findByStatusWithAttemptsLessThan5(notificationStatus.toString().toLowerCase());
    }

}
