package org.example.notificationrebalancer.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic newTopic(){
        return new NewTopic("notifications_to_user",5,(short) 1);
    }
}
