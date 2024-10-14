package org.coursework.notificationservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "notifications",groupId = "consumer1")
    public void listen(Map<String,String> message){
        System.out.println(message.toString());
    }

}
