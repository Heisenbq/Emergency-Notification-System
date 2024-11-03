package org.coursework.notificationservice.senderService;

import org.springframework.stereotype.Service;

@Service
public class SmsService extends NotificationSenderService {
    @Override
    public void sendNotificationToGroup(Long groupId, Long templateId) {

    }
}
