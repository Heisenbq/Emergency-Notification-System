package org.examplefghjf.serviceapi.kafka;

import org.examplefghjf.serviceapi.db.entity.Group;
import org.examplefghjf.serviceapi.db.entity.NotificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String,Map<String,String>> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, Map<String,String>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Map<String, String> message) {
        List<String> keys = Arrays.asList("key1", "key2", "key3", "key4", "key5");
        Random random = new Random();
        String key = keys.get(random.nextInt(keys.size()));
        kafkaTemplate.send("notifications",key,message);
    }
}
