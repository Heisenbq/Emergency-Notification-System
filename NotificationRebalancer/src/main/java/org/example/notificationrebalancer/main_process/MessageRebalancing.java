package org.example.notificationrebalancer.main_process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.notificationrebalancer.Enums.NotificationStatus;
import org.example.notificationrebalancer.db.entity.Contact;
import org.example.notificationrebalancer.db.entity.Notification;
import org.example.notificationrebalancer.db.entity.NotificationSession;
import org.example.notificationrebalancer.db.entity.NotificationTemplate;
import org.example.notificationrebalancer.db.repository.NotificationTemplateRepository;
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
    private final NotificationTemplateRepository notificationTemplateRepository;

    @Autowired
    public MessageRebalancing(KafkaService kafkaService, NotificationService notificationService
            ,ContactService contactService,NotificationSessionService notificationSessionService
            ,NotificationTemplateRepository notificationTemplateRepository) {
        this.kafkaService = kafkaService;
        this.notificationService = notificationService;
        this.contactService = contactService;
        this.notificationSessionService = notificationSessionService;
        this.notificationTemplateRepository = notificationTemplateRepository;
    }

    // once in 30 sec
    @Scheduled(fixedRate = 30000)
    public void rebalanceMessages() {
        System.out.println("ioio: ");
        try {
            List<Notification> notifications= notificationService.getNotificationsByStatus(NotificationStatus.FAILED);
            System.out.println(notifications.toString());
            notifications.stream()
                    .forEach(notification -> {
                        try {
                            NotificationTemplate notificationTemplate = notificationTemplateRepository
                                    .findById(notificationSessionService
                                            .getNotificationSessionById(notification.getSessionId())
                                            .getTemplateId()
                                    ).orElseThrow();

                            kafkaService.send(notification
                                    ,notificationTemplate);

                        }catch (JsonProcessingException exception) {
                            exception.printStackTrace();
                        }
                    });
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
