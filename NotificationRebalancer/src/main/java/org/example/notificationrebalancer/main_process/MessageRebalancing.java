package org.example.notificationrebalancer.main_process;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.notificationrebalancer.Enums.NotificationStatus;
import org.example.notificationrebalancer.db.entity.Contact;
import org.example.notificationrebalancer.db.entity.Notification;
import org.example.notificationrebalancer.db.entity.NotificationSession;
import org.example.notificationrebalancer.kafka.KafkaService;
import org.example.notificationrebalancer.service.ContactService;
import org.example.notificationrebalancer.service.NotificationService;
import org.example.notificationrebalancer.service.NotificationSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageRebalancing {
    private final KafkaService kafkaService;
    private final NotificationService notificationService;
    private final ContactService contactService;
    private final NotificationSessionService notificationSessionService;

    @Autowired
    public MessageRebalancing(KafkaService kafkaService, NotificationService notificationService,ContactService contactService,NotificationSessionService notificationSessionService) {
        this.kafkaService = kafkaService;
        this.notificationService = notificationService;
        this.contactService = contactService;
        this.notificationSessionService = notificationSessionService;
    }

    // once in 30 sec
    @Scheduled(fixedRate = 30000)
    public void rebalanceMessages() {
        try {
            List<Notification> notifications= notificationService.getNotificationsByStatus(NotificationStatus.FAILED);
            notifications.stream()
                    .forEach(notification -> {
                        kafkaService.send(notification.getContactId()
                                ,notificationSessionService
                                        .getNotificationSessionById(notification.getSessionId()).getTemplateId());
                    });
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
