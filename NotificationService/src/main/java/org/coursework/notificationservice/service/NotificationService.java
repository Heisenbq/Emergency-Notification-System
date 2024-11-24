package org.coursework.notificationservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Not;
import org.coursework.notificationservice.Enums.NotificationStatus;
import org.coursework.notificationservice.db.entity.Contact;
import org.coursework.notificationservice.db.entity.Notification;
import org.coursework.notificationservice.db.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void createNotification(Notification notification){
        try {
            notificationRepository.save(notification);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException("Уникальность каких то данных была нарушена!");
        }
    }

    public void createNotificationInfo(Long sessionId, Long contactId, NotificationStatus status,String errorMessage){
        Notification notification = new Notification();
        notification.setSessionId(sessionId);
        notification.setContactId(contactId                                                                                                                                                                 );
        notification.setStatus(status.toString().toLowerCase());
        notification.setErrorMessage(errorMessage);
        notification.setSendAttempts(1);
        notification.setLastAttemptAt(Timestamp.valueOf(LocalDateTime.now()));
        try {
            notificationRepository.save(notification);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException("Уникальность каких то данных была нарушена!");
        }
    }

    public void updateStatus(Notification notification,NotificationStatus status) {
        Notification updatedNotification = notificationRepository.findById(notification.getId()).orElseThrow();
        updatedNotification.setStatus(status.toString().toLowerCase());
        updatedNotification.setSendAttempts(updatedNotification.getSendAttempts()+1);
        if (status==NotificationStatus.SENT) {
            updatedNotification.setErrorMessage("повтороно удалось");
        }
        notificationRepository.save(updatedNotification);
    }
    public static Notification parseFromJsonRetryMessages(String json) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        // Разбираем JSON в JsonNode
        JsonNode rootNode = objectMapper.readTree(json);

        // Извлекаем нужные поля
        Long id = rootNode.get("id").asLong();
        Long sessionId = rootNode.get("sessionId").asLong();
        Long contactId = rootNode.path("contact").get("id").asLong();
        String status = rootNode.get("status").asText();
        String errorMessage = rootNode.get("errorMessage").asText();

        // Печатаем извлечённые значения
        Notification notification = new Notification();
        notification.setId(id);
        notification.setSessionId(sessionId);
        notification.setContactId(contactId);
        notification.setStatus(status);
        return notification;
    }

    public static Contact getContactOfNotificationByJSON(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Разбираем JSON в JsonNode
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode contactNode = rootNode.path("contact");

        // Печатаем все данные о контакте
        Long contactId = contactNode.get("id").asLong();
        String contactName = contactNode.get("contactName").asText();
        String email = contactNode.get("email").asText();
        String phone = contactNode.get("phone").asText();
        String status = contactNode.get("status").asText();

        Contact contact = new Contact();
        contact.setId(contactId);
        contact.setContactName(contactName);
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setStatus(status);
        return contact;
    }
}
