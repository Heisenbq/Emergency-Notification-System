package org.coursework.notificationservice.senderService;

import org.coursework.notificationservice.db.entity.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsService extends NotificationSenderService {
    @Value("${epochta.api.url}")
    private String apiUrl;
    @Value("${epochta.api.key}")
    private String apiKey;
    private final RestTemplate restTemplate;

    public SmsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void send(Contact contact, String subject, String message) {
        // Формируем URL и параметры запроса
        String url = apiUrl + "/send"; // Пример: /send

        // Тело запроса
        String jsonPayload = String.format("{\"phone\": \"%s\", \"message\": \"%s\"}", contact.getPhone(), message);

        // Заголовки запроса
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

        // Отправка POST-запроса
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response);
    }
}
