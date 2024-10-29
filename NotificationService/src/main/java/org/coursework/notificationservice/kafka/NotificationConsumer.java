package org.coursework.notificationservice.kafka;

import org.coursework.notificationservice.db.entity.Contact;
import org.coursework.notificationservice.db.entity.Group;
import org.coursework.notificationservice.db.repository.GroupRepository;
import org.coursework.notificationservice.senderService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class NotificationConsumer {

    private final EmailService emailService;

    private final GroupRepository groupRepository;


    @Autowired
    public NotificationConsumer(EmailService emailService,GroupRepository groupRepository) {
        this.emailService = emailService;
        this.groupRepository = groupRepository;
    }

    @KafkaListener(topics = "notifications",groupId = "consumer1")
    public void listen(Map<String,String> message){
        System.out.println(message.toString());
        String groupId = message.get("group_id");
        String templateId = message.get("template_id");
        try {
            emailService.sendNotificationToGroup(groupId,templateId);
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
        } catch (MailSendException ex){
            ex.printStackTrace();
        }
    }

}
