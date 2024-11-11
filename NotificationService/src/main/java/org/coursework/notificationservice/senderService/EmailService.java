package org.coursework.notificationservice.senderService;

import org.coursework.notificationservice.Enums.NotificationStatus;
import org.coursework.notificationservice.db.entity.Contact;
import org.coursework.notificationservice.db.entity.Group;
import org.coursework.notificationservice.db.entity.NotificationSession;
import org.coursework.notificationservice.db.entity.NotificationTemplate;
import org.coursework.notificationservice.db.repository.GroupRepository;
import org.coursework.notificationservice.db.repository.NotificationTemplateRepository;
import org.coursework.notificationservice.service.NotificationService;
import org.coursework.notificationservice.service.NotificationSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EmailService extends NotificationSenderService {
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender, NotificationTemplateRepository notificationTemplateRepository, GroupRepository groupRepository) {
        this.mailSender = mailSender;
        this.notificationTemplateRepository = notificationTemplateRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public void send(String toAddress,String subject,String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toAddress);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

//    @Override
//    public void sendNotificationToGroup(Long groupId, Long templateId)  {
//        Group group = groupRepository.findById(groupId).orElseThrow();
//        NotificationTemplate notificationTemplate = notificationTemplateRepository.findById(templateId).orElseThrow();
//        Set<Contact> contactsInGroup = group.getContacts();
//        String message = notificationTemplate.getText();
//
//        // создание сессии нотификации
//        NotificationSession notificationSession = notificationSessionService.createSession(groupId,templateId);
//
//        // send notification
//        contactsInGroup.stream().
//                forEach(
//                        contact -> {
//
//                                try {
//                                    send(contact.getEmail(),
//                                            notificationTemplate.getName(),
//                                            contact.getContactName() + "! " + message);
//
//                                    // создание информации о нотификации
//                                    notificationService.createNotification(notificationSession.getId(),contact.getId(), NotificationStatus.SENT,"дошло жиесть");
//                                } catch (RuntimeException exception) {
//                                    // создание информации о нотификации
//                                    notificationService.createNotification(notificationSession.getId(),contact.getId(), NotificationStatus.FAILED,"не дошло жиесть");
//                                    System.err.println(exception.getMessage());
//                                }
//
//                        }
//                );
//    }
}
