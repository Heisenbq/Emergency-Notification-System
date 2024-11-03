package org.coursework.notificationservice.senderService;

import org.coursework.notificationservice.service.NotificationService;
import org.coursework.notificationservice.service.NotificationSessionService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class NotificationSenderService {
    @Autowired
    protected NotificationService notificationService;
    @Autowired
    protected NotificationSessionService notificationSessionService;



    abstract void sendNotificationToGroup(Long groupId, Long templateId);
}
