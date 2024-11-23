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
        return notificationRepository.findByStatus(notificationStatus.toString().toLowerCase());
    }



//    public void createNotification(Notification notification){
//        try {
//            notificationRepository.save(notification);
//        } catch (DataIntegrityViolationException exception) {
//            throw new DataIntegrityViolationException("Уникальность каких то данных была нарушена!");
//        }
//    }
//
//    public void createNotification(Long sessionId, Long contactId, NotificationStatus status, String errorMessage){
//        Notification notification = new Notification();
//        notification.setSessionId(sessionId);
//        notification.setContactId(contactId                                                                                                                                                                 );
//        notification.setStatus(status.toString().toLowerCase());
//        notification.setErrorMessage(errorMessage);
//
//        try {
//            notificationRepository.save(notification);
//        } catch (DataIntegrityViolationException exception) {
//            throw new DataIntegrityViolationException("Уникальность каких то данных была нарушена!");
//        }
//    }
}
