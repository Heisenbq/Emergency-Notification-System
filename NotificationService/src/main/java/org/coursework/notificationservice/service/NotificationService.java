package org.coursework.notificationservice.service;

import org.coursework.notificationservice.Enums.NotificationStatus;
import org.coursework.notificationservice.db.entity.Notification;
import org.coursework.notificationservice.db.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void createNotification(Notification notification){
        try {
            notificationRepository.save(notification);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException("Уникальность каких то данных была нарушена!");
        }
    }

    public void createNotification(Long sessionId, Long contactId, NotificationStatus status,String errorMessage){
        Notification notification = new Notification();
        notification.setSessionId(sessionId);
        notification.setContactId(contactId);
        notification.setStatus(status.toString().toLowerCase());
        notification.setErrorMessage(errorMessage);

        try {
            notificationRepository.save(notification);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException("Уникальность каких то данных была нарушена!");
        }
    }
}
