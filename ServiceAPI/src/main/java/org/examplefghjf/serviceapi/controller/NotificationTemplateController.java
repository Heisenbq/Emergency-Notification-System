package org.examplefghjf.serviceapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.examplefghjf.serviceapi.db.entity.NotificationTemplate;
import org.examplefghjf.serviceapi.exception.DuplicateDataException;
import org.examplefghjf.serviceapi.service.NotificationTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Tag(name = "NotificationTemplateController",description = "CRUD операции над шаблонами уведомлений")
@RestController
@RequestMapping("/notification-templates")
public class NotificationTemplateController {
    private final NotificationTemplateService notificationTemplateService;
    @Autowired
    public NotificationTemplateController(NotificationTemplateService notificationTemplateService) {
        this.notificationTemplateService = notificationTemplateService;
    }

    @Operation(summary = "получить список всех шаблонов уведомлений")
    @GetMapping()
    public ResponseEntity<List<NotificationTemplate>> getAll(){
        return new ResponseEntity<>(notificationTemplateService.getAllTemplates(), HttpStatus.OK);
    }

    @Operation(summary = "получить шаблон уведомления")
    @GetMapping("/{id}")
    public ResponseEntity<NotificationTemplate> getById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(notificationTemplateService.getTemplate(id),HttpStatus.OK);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(new NotificationTemplate(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Создание нового шаблона уведомления")
    @PostMapping()
    public ResponseEntity<NotificationTemplate> create(@RequestBody NotificationTemplate notificationTemplate){
        try {
            return new ResponseEntity<>(notificationTemplateService.createTemplate(notificationTemplate),HttpStatus.OK);
        }catch (DuplicateDataException exception){
            return new ResponseEntity<>(new NotificationTemplate(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Обновление шаблона уведомления")
    @PutMapping("/{id}")
    public ResponseEntity<NotificationTemplate> update(@PathVariable Long id,@RequestBody NotificationTemplate notificationTemplate){
        try {
            return new ResponseEntity<>(notificationTemplateService.updateTemplate(id,notificationTemplate),HttpStatus.OK);
        }catch (DuplicateDataException exception){
            return new ResponseEntity<>(new NotificationTemplate(),HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException exception){
            return new ResponseEntity<>(new NotificationTemplate(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Operation(summary = "Удаление шаблона уведомления")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try {
            notificationTemplateService.deleteTemplate(id);
            return new ResponseEntity<>("успешное удаление!",HttpStatus.OK);
        } catch (NoSuchElementException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
