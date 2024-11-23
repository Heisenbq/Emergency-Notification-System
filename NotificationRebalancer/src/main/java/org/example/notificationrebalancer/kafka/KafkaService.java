package org.example.notificationrebalancer.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
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


    public void send(Long contactId,Long templateId) {
        try {
            Map<String,String> map = new HashMap<>();
            map.put("contact_id",contactId.toString());
            map.put("template_id",templateId.toString());
            kafkaProducer.sendMessage(map);
        }catch (Exception ex){
            System.err.println(ex);
        }
    }
}
