package org.coursework.notificationservice.kafka;

import org.coursework.notificationservice.db.entity.Contact;
import org.coursework.notificationservice.db.entity.Group;
import org.coursework.notificationservice.db.entity.NotificationTemplate;
import org.coursework.notificationservice.db.repository.GroupRepository;
import org.coursework.notificationservice.db.repository.NotificationTemplateRepository;
import org.coursework.notificationservice.senderService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class NotificationConsumer {

    private final EmailService emailService;
    private final GroupRepository groupRepository;
    @Autowired
    private NotificationTemplateRepository notificationTemplateRepository;

    @Autowired
    public NotificationConsumer(EmailService emailService,GroupRepository groupRepository) {
        this.emailService = emailService;
        this.groupRepository = groupRepository;
    }

    @KafkaListener(topics = "notifications",groupId = "consumer1")
    public void listen(Map<String,String> message){
        System.out.println(message.toString());
       sendNotificationToGroup(message.get("group_id"),message.get("template_id"));

        sendNotification("ty gandon");
    }

    private void sendNotification(String message){
        // send notification
        emailService.send("a.gadjiev1@mail.ru","uiuiu",message);
    }
    private void sendNotificationToGroup(String groupId,String templateId) {
        Group group = groupRepository.findById(Long.parseLong(groupId)).orElseThrow();
        NotificationTemplate notificationTemplate = notificationTemplateRepository.findById(Long.parseLong(templateId)).orElseThrow();
        Set<Contact> contactsInGroup = group.getContacts();
        String message = notificationTemplate.getText();
        // send notification
        contactsInGroup.stream().
                forEach(
                        contact -> emailService.send(contact.getEmail(),
                                 notificationTemplate.getName(),
                                 contact.getContactName() + "! " + message)
                );
    }


}
