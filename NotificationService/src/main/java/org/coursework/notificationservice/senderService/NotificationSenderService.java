package org.coursework.notificationservice.senderService;

public interface NotificationSenderService {
    void sendNotificationToGroup(Long groupId,Long templateId);
}
