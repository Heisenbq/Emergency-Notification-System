package org.examplefghjf.serviceapi.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class KafkaService {
    private final KafkaProducer kafkaProducer;

    @Autowired
    public KafkaService(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void send(HashMap<String, String> message){
        kafkaProducer.sendMessage(message);
    }
}
