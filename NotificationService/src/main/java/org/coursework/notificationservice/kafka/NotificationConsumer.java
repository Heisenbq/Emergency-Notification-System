package org.coursework.notificationservice.kafka;

import org.coursework.notificationservice.senderService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class NotificationConsumer {

    private final EmailService emailService;


    @Autowired
    public NotificationConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "notifications",groupId = "consumer1")
    public void listen(Map<String,String> message){
        System.out.println(message.toString());
        String groupId = message.get("group_id");
        String templateId = message.get("template_id");



//        emailService.send("a.gadjiev1@mail.ru","uiuiu","hyila");


        try {
            emailService.sendNotificationToGroup(groupId,templateId);
//            emailService.send("a.gadjiev1@mail.ru","uiuiu","hyila");
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
        } catch (MailSendException ex){
            ex.printStackTrace();
        }
    }

}
