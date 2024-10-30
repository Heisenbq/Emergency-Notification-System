package org.examplefghjf.serviceapi.service;

import org.examplefghjf.serviceapi.db.entity.NotificationSession;
import org.examplefghjf.serviceapi.db.repository.NotificationSessionRepository;
import org.examplefghjf.serviceapi.exception.DuplicateDataException;
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

    public void createSession(NotificationSession notificationSession) throws  DuplicateDataException{
        try {
            notificationSessionRepository.save(notificationSession);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateDataException("Уникальность каких то данных была нарушена!");
        }
    }
    public void createSession(Long groupId,Long templateId) throws  DuplicateDataException{
        try {
            NotificationSession notificationSession = new NotificationSession();
            notificationSession.setGroupId(groupId);
            notificationSession.setTemplateId(templateId);
            notificationSessionRepository.save(notificationSession);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateDataException("Уникальность каких то данных была нарушена!");
        }
    }
}
