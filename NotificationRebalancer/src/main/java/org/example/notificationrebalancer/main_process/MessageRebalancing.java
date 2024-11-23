package org.example.notificationrebalancer.main_process;

import org.example.notificationrebalancer.Enums.NotificationStatus;
import org.example.notificationrebalancer.db.entity.Notification;
import org.example.notificationrebalancer.kafka.KafkaService;
import org.example.notificationrebalancer.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class MessageRebalancing {
    private final KafkaService kafkaService;
    private final NotificationService notificationService;

    @Autowired
    public MessageRebalancing(KafkaService kafkaService, NotificationService notificationService) {
        this.kafkaService = kafkaService;
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = 5000)
    public void rebalanceMessages() {
        try {
            List<Notification> notifications= notificationService.getNotificationsByStatus(NotificationStatus.FAILED);
            kafkaService.send(notifications);
        }catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }
}
