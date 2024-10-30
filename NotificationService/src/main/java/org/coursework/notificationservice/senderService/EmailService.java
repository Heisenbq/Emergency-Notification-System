package org.coursework.notificationservice.senderService;

import org.coursework.notificationservice.db.entity.Contact;
import org.coursework.notificationservice.db.entity.Group;
import org.coursework.notificationservice.db.entity.NotificationTemplate;
import org.coursework.notificationservice.db.repository.GroupRepository;
import org.coursework.notificationservice.db.repository.NotificationTemplateRepository;
import org.coursework.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EmailService implements NotificationSenderService {
    private final JavaMailSender mailSender;
    private final NotificationTemplateRepository notificationTemplateRepository;
    private final GroupRepository groupRepository;

    private  final NotificationService notificationService;

    @Autowired
    public EmailService(JavaMailSender mailSender, NotificationTemplateRepository notificationTemplateRepository, GroupRepository groupRepository, NotificationService notificationService) {
        this.mailSender = mailSender;
        this.notificationTemplateRepository = notificationTemplateRepository;
        this.groupRepository = groupRepository;
        this.notificationService = notificationService;
    }

    public void send(String toAddress,String subject,String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toAddress);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    @Override
    public void sendNotificationToGroup(Long groupId, Long templateId)  {
        Group group = groupRepository.findById(groupId).orElseThrow();
        NotificationTemplate notificationTemplate = notificationTemplateRepository.findById(templateId).orElseThrow();
        Set<Contact> contactsInGroup = group.getContacts();
        String message = notificationTemplate.getText();
        // send notification
        contactsInGroup.stream().
                forEach(
                        contact -> {

                                try {
                                    send(contact.getEmail(),
                                            notificationTemplate.getName(),
                                            contact.getContactName() + "! " + message);
                                } catch (RuntimeException exception) {

                                    System.err.println(exception.getMessage());
                                }

                        }
                );
    }
}
