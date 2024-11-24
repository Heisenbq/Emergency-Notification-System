package org.coursework.notificationservice;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class NotificationServiceApplication {
//	@Bean
//	public FirebaseMessaging firebaseMessaging() throws IOException {
//		// Путь к вашему сервисному ключу
//		FileInputStream serviceAccount =
//				new FileInputStream("firebase-service-account.json");
//
//		FirebaseOptions options = new FirebaseOptions.Builder()
//				.setCredentials(FirebaseApp.getInstance().getOptions().getCredentials())
//				.build();
//
//		FirebaseApp.initializeApp(options);
//
//		return FirebaseMessaging.getInstance();
//	}

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
