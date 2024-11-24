package org.coursework.notificationservice.senderService;

import org.coursework.notificationservice.Enums.NotificationStatus;
import org.coursework.notificationservice.db.entity.*;
import org.coursework.notificationservice.db.repository.GroupRepository;
import org.coursework.notificationservice.db.repository.NotificationTemplateRepository;
import org.coursework.notificationservice.service.NotificationService;
import org.coursework.notificationservice.service.NotificationSessionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public abstract class NotificationSenderService {
    @Autowired
    protected NotificationService notificationService;
    @Autowired
    protected NotificationSessionService notificationSessionService;

    public final void sendNotificationToGroup(Group group, NotificationTemplate notificationTemplate)  {
        Set<Contact> contactsInGroup = group.getContacts();
        String message = notificationTemplate.getText();

        // создание сессии нотификации
        NotificationSession notificationSession = notificationSessionService.createSession(group.getId(),notificationTemplate.getId());

        // send notification
        contactsInGroup.stream().
                forEach(
                        contact -> {

                            try {
                                send(contact,
                                        notificationTemplate.getName(),
                                        contact.getContactName() + "! " + message);

                                // создание информации о нотификации
                                notificationService.createNotificationInfo(notificationSession.getId(),contact.getId(), NotificationStatus.SENT,"дошло жиесть");
                            } catch (RuntimeException exception) {
                                // создание информации о нотификации
                                notificationService.createNotificationInfo(notificationSession.getId(),contact.getId(), NotificationStatus.FAILED,"не дошло жиесть");
                                System.err.println(exception.getMessage());
                            }

                        }
                );
    }

    public void resend(Contact contact, Notification notification, String subject, String message) {
        try {
            send(contact,subject,message);
            notificationService.updateStatus(notification,NotificationStatus.SENT);
        }catch (RuntimeException exception) {
            notificationService.updateStatus(notification,NotificationStatus.FAILED);
            System.out.println("------------ASDDDDDDDDDDDD-----------");;
        }
    }
    abstract void send(Contact contact,String subject,String message);
}
