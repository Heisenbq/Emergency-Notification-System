package org.coursework.notificationservice.senderService;

import org.springframework.stereotype.Service;

@Service
public class SmsService implements NotificationService{
    @Override
    public void sendNotificationToGroup(String groupId, String templateId) {

    }
}
