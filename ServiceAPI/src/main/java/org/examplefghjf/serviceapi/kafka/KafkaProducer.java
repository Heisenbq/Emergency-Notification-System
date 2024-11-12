package org.examplefghjf.serviceapi.kafka;

import org.examplefghjf.serviceapi.db.entity.Group;
import org.examplefghjf.serviceapi.db.entity.NotificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String,Map<String,String>> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, Map<String,String>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Map<String, String> message) {
        kafkaTemplate.send("notifications","key1",message );
    }
}
