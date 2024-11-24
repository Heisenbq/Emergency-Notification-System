package org.coursework.notificationservice.service;

import org.coursework.notificationservice.Enums.NotificationSessionStatus;
import org.coursework.notificationservice.db.entity.NotificationSession;
import org.coursework.notificationservice.db.repository.NotificationSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class NotificationSessionService {
    private final NotificationSessionRepository notificationSessionRepository;

    @Autowired
    public NotificationSessionService(NotificationSessionRepository notificationSessionRepository) {
        this.notificationSessionRepository = notificationSessionRepository;
    }

    public void createSession(NotificationSession notificationSession) throws  DataIntegrityViolationException{
        try {
            notificationSessionRepository.save(notificationSession);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException("Уникальность каких то данных была нарушена!");
        }
    }
    public NotificationSession createSession(Long groupId,Long templateId) throws RuntimeException{
        try {
            NotificationSession notificationSession = new NotificationSession();
            notificationSession.setGroupId(groupId);
            notificationSession.setTemplateId(templateId);
            notificationSession.setStatus(NotificationSessionStatus.PENDING.getStatus());

            return notificationSessionRepository.save(notificationSession);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException("Уникальность каких то данных была нарушена!");
        }
    }
}
