package org.coursework.notificationservice.senderService;

public interface NotificationService {
    void sendNotificationToGroup(Long groupId,Long templateId);
}
