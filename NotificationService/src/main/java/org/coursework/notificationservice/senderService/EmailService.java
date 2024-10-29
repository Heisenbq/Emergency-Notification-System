package org.coursework.notificationservice.senderService;

import org.coursework.notificationservice.db.entity.Contact;
import org.coursework.notificationservice.db.entity.Group;
import org.coursework.notificationservice.db.entity.NotificationTemplate;
import org.coursework.notificationservice.db.repository.GroupRepository;
import org.coursework.notificationservice.db.repository.NotificationTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class EmailService implements NotificationService{
    private final JavaMailSender mailSender;
    private final NotificationTemplateRepository notificationTemplateRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public EmailService(JavaMailSender mailSender, NotificationTemplateRepository notificationTemplateRepository, GroupRepository groupRepository) {
        this.mailSender = mailSender;
        this.notificationTemplateRepository = notificationTemplateRepository;
        this.groupRepository = groupRepository;
    }

    public void send(String toAddress,String subject,String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toAddress);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    @Override
    public void sendNotificationToGroup(String groupId, String templateId) throws NoSuchElementException, MailSendException {
        Group group = groupRepository.findById(Long.parseLong(groupId)).orElseThrow();
        NotificationTemplate notificationTemplate = notificationTemplateRepository.findById(Long.parseLong(templateId)).orElseThrow();
        Set<Contact> contactsInGroup = group.getContacts();
        String message = notificationTemplate.getText();
        // send notification
        contactsInGroup.stream().
                forEach(
                        contact -> send(contact.getEmail(),
                                notificationTemplate.getName(),
                                contact.getContactName() + "! " + message)
                );
    }
}
