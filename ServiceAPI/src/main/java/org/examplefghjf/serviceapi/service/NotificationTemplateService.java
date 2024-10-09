package org.examplefghjf.serviceapi.service;

import org.examplefghjf.serviceapi.db.entity.NotificationTemplate;
import org.examplefghjf.serviceapi.db.repository.NotificationTemplateRepository;
import org.examplefghjf.serviceapi.exception.DuplicateDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NotificationTemplateService {
    private final NotificationTemplateRepository notificationTemplateRepository;
    @Autowired
    public NotificationTemplateService(NotificationTemplateRepository notificationTemplateRepository) {
        this.notificationTemplateRepository = notificationTemplateRepository;
    }

    public List<NotificationTemplate> getAllTemplates(){
        return notificationTemplateRepository.findAll();
    }

    public NotificationTemplate getTemplate(Long id) throws NoSuchElementException {
        return notificationTemplateRepository.findById(id).orElseThrow();
    }

    public NotificationTemplate createTemplate(NotificationTemplate template) throws  DuplicateDataException{
        if (template.getId()!=null) throw new IllegalArgumentException("В теле запроса не должно быть id!");
        try{
            return notificationTemplateRepository.save(template);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateDataException("Уникальность каких то данных была нарушена!");
        }
    }

    public NotificationTemplate updateTemplate(Long id,NotificationTemplate template) throws NoSuchElementException,DuplicateDataException{
        NotificationTemplate existedTemplate = notificationTemplateRepository.findById(id).orElseThrow();
        existedTemplate.setName(template.getName());
        existedTemplate.setText(template.getText());
        existedTemplate.setType(template.getType());
        try{
            return notificationTemplateRepository.save(existedTemplate);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateDataException("Уникальность каких то данных была нарушена!");
        }
    }

    public void deleteTemplate(Long id) throws NoSuchElementException{
        notificationTemplateRepository.findById(id).orElseThrow();
        notificationTemplateRepository.deleteById(id);
    }
}
