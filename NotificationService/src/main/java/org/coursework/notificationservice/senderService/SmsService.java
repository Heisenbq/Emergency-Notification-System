package org.coursework.notificationservice.senderService;

import org.springframework.stereotype.Service;

@Service
public class SmsService extends NotificationSenderService {
    @Override
    void send(String toAddress, String subject, String message) {

    }
}
