package org.example.notificationrebalancer.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic newTopic(){
        return new NewTopic("retry_notifications",1,(short) 1);
    }
}
