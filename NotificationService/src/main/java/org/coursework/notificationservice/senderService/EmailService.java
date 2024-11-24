package org.coursework.notificationservice.senderService;

import org.coursework.notificationservice.Enums.NotificationStatus;
import org.coursework.notificationservice.db.entity.*;
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
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(Contact contact,String subject,String message){
        String toAddress = contact.getEmail();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toAddress);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}
