package org.example.notificationrebalancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotificationRebalancerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationRebalancerApplication.class, args);
    }

}
