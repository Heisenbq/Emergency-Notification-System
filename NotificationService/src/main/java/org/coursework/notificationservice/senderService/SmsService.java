package org.coursework.notificationservice.senderService;

import org.coursework.notificationservice.db.entity.Contact;
import org.springframework.stereotype.Service;

@Service
public class SmsService extends NotificationSenderService {
    @Override
    void send(Contact contact, String subject, String message) {

    }
}
