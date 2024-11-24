package org.coursework.notificationservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.coursework.notificationservice.db.entity.*;
import org.coursework.notificationservice.db.repository.GroupRepository;
import org.coursework.notificationservice.db.repository.NotificationTemplateRepository;
import org.coursework.notificationservice.senderService.EmailService;
import org.coursework.notificationservice.senderService.NotificationSenderService;
import org.coursework.notificationservice.senderService.SmsService;
import org.coursework.notificationservice.service.NotificationService;
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
    private final SmsService smsService;

    private final GroupRepository groupRepository;
    private final NotificationTemplateRepository notificationTemplateRepository;


    @Autowired
    public NotificationConsumer(EmailService emailService,SmsService smsService,GroupRepository groupRepository,NotificationTemplateRepository notificationTemplateRepository) {
        this.emailService = emailService;
        this.smsService = smsService;
        this.groupRepository = groupRepository;
        this.notificationTemplateRepository = notificationTemplateRepository;
    }

    @KafkaListener(topics = "notifications",groupId = "consumer1",concurrency = "5")
    public void sender(Map<String,String> message) throws RuntimeException {
        System.out.println(message.toString());
        Group group = groupRepository
                .findById(Long.parseLong(message.get("group_id"))).orElseThrow();
        NotificationTemplate template = notificationTemplateRepository
                .findById(Long.parseLong(message.get("template_id"))).orElseThrow();

        switch (template.getType().toLowerCase()){
            case "email":{
                emailService.sendNotificationToGroup(group,template);
                break;
            }
            case "sms": {
                smsService.sendNotificationToGroup(group,template);
                break;
            }
        }
    }

    @KafkaListener(topics = "retry_notifications",groupId = "consumer2")
    public void retry(Map<String,String> message) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Notification notification = NotificationService.parseFromJsonRetryMessages(message.get("notification"));
        Contact contact = NotificationService.getContactOfNotificationByJSON(message.get("notification"));
        NotificationTemplate template = objectMapper.readValue(message.get("template"), NotificationTemplate.class);
        switch (template.getType().toLowerCase()){
            case "email":{
                emailService.resend(contact,notification,template.getName(), template.getText());
                break;
            }
            case "sms": {
                smsService.resend(contact,notification,template.getName(), template.getText());
                break;
            }
        }
    }

}
