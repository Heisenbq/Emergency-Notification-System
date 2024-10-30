package org.coursework.notificationservice.kafka;

import org.coursework.notificationservice.db.entity.NotificationSession;
import org.coursework.notificationservice.senderService.EmailService;
import org.coursework.notificationservice.service.NotificationSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class NotificationConsumer {

    private final EmailService emailService;
    private final NotificationSessionService notificationSessionService;

    @Autowired
    public NotificationConsumer(EmailService emailService,NotificationSessionService notificationSessionService) {
        this.emailService = emailService;
        this.notificationSessionService = notificationSessionService;
    }

    @KafkaListener(topics = "notifications",groupId = "consumer1")
    public void listen(Map<String,String> message){
        System.out.println(message.toString());
        Long groupId = Long.parseLong(message.get("group_id"));
        Long templateId = Long.parseLong(message.get("template_id"));

        try {
            notificationSessionService.createSession(groupId,templateId);
            emailService.sendNotificationToGroup(groupId,templateId);
        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }
    }

}
