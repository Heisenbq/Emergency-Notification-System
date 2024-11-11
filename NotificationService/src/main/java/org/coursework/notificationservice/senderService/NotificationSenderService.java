package org.coursework.notificationservice.senderService;

import org.coursework.notificationservice.Enums.NotificationStatus;
import org.coursework.notificationservice.db.entity.Contact;
import org.coursework.notificationservice.db.entity.Group;
import org.coursework.notificationservice.db.entity.NotificationSession;
import org.coursework.notificationservice.db.entity.NotificationTemplate;
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
    @Autowired
    protected NotificationTemplateRepository notificationTemplateRepository;
    @Autowired
    protected GroupRepository groupRepository;

    public final void sendNotificationToGroup(Long groupId, Long templateId)  {
        Group group = groupRepository.findById(groupId).orElseThrow();
        NotificationTemplate notificationTemplate = notificationTemplateRepository.findById(templateId).orElseThrow();
        Set<Contact> contactsInGroup = group.getContacts();
        String message = notificationTemplate.getText();

        // создание сессии нотификации
        NotificationSession notificationSession = notificationSessionService.createSession(groupId,templateId);

        // send notification
        contactsInGroup.stream().
                forEach(
                        contact -> {

                            try {
                                send(contact,
                                        notificationTemplate.getName(),
                                        contact.getContactName() + "! " + message);

                                // создание информации о нотификации
                                notificationService.createNotification(notificationSession.getId(),contact.getId(), NotificationStatus.SENT,"дошло жиесть");
                            } catch (RuntimeException exception) {
                                // создание информации о нотификации
                                notificationService.createNotification(notificationSession.getId(),contact.getId(), NotificationStatus.FAILED,"не дошло жиесть");
                                System.err.println(exception.getMessage());
                            }

                        }
                );
    }

    abstract void send(Contact contact,String subject,String message);
}
