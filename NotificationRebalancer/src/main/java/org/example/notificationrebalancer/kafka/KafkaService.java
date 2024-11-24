package org.example.notificationrebalancer.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.example.notificationrebalancer.db.entity.Contact;
import org.example.notificationrebalancer.db.entity.NotificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaService {
    private final KafkaProducer kafkaProducer;

    @Autowired
    public KafkaService(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    public void send(Contact contact, NotificationTemplate notificationTemplate) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String formattedContact = objectMapper.writeValueAsString(contact);
        String formattedTemplate = objectMapper.writeValueAsString(notificationTemplate);
        Map<String,String> map = new HashMap<>();
        map.put("contact",formattedContact);
        map.put("template",formattedTemplate);
        kafkaProducer.sendMessage(map);
    }
}
