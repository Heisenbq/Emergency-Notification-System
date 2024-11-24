package org.coursework.notificationservice.senderService;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationService {
//    private final FirebaseMessaging firebaseMessaging;
//
//    @Autowired
//    public PushNotificationService(FirebaseMessaging firebaseMessaging) {
//        this.firebaseMessaging = firebaseMessaging;
//    }
//
//    public String sendPushNotification(String targetToken, String title, String body) {
//        // Создаем сообщение с уведомлением
//        Message message = Message.builder()
//                .setToken(targetToken)  // Токен устройства получателя
//                .setNotification(Notification.builder()
//                        .setTitle(title)      // Заголовок уведомления
//                        .setBody(body)        // Текст уведомления
//                        .build())
//                .build();
//
//        try {
//            // Отправка сообщения
//            return firebaseMessaging.send(message);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error sending notification: " + e.getMessage();
//        }
//    }
}
