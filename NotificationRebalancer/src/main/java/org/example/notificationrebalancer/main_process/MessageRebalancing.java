package org.example.notificationrebalancer.main_process;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.notificationrebalancer.Enums.NotificationStatus;
import org.example.notificationrebalancer.db.entity.Contact;
import org.example.notificationrebalancer.db.entity.Notification;
import org.example.notificationrebalancer.kafka.KafkaService;
import org.example.notificationrebalancer.service.ContactService;
import org.example.notificationrebalancer.service.NotificationService;
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

    @Autowired
    public MessageRebalancing(KafkaService kafkaService, NotificationService notificationService,ContactService contactService) {
        this.kafkaService = kafkaService;
        this.notificationService = notificationService;
        this.contactService = contactService;
    }

    @Scheduled(fixedRate = 5000)
    public void rebalanceMessages() {
        try {
            List<Notification> notifications= notificationService.getNotificationsByStatus(NotificationStatus.FAILED);
            List<Contact> contacts = new ArrayList<>();
            notifications.stream()
                    .forEach(notification -> {
                        contacts.add(contactService.getContactById(notification.getContactId()));
                    });
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonFormattedContacts = objectMapper.writeValueAsString(contacts);

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
