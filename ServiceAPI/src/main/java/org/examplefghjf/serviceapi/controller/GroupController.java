package org.examplefghjf.serviceapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.examplefghjf.serviceapi.db.entity.Group;
import org.examplefghjf.serviceapi.db.entity.NotificationTemplate;
import org.examplefghjf.serviceapi.exception.DuplicateDataException;
import org.examplefghjf.serviceapi.kafka.KafkaService;
import org.examplefghjf.serviceapi.service.GroupService;
import org.examplefghjf.serviceapi.service.NotificationTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Tag(name = "GroupController", description = "CRUD над группой, без доступа к пользователям")
@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final KafkaService kafkaService;

    private final NotificationTemplateService notificationTemplateService;
    @Autowired
    public GroupController(GroupService groupService,KafkaService kafkaService,NotificationTemplateService notificationTemplateService) {
        this.groupService = groupService;
        this.kafkaService = kafkaService;
        this.notificationTemplateService=notificationTemplateService;
    }

    @Operation(summary = "получить список групп")
    @GetMapping()
    public List<Group> getAll(){
        return groupService.getAllGroups();
    }

    @Operation(summary = "получить группу по id")
    @GetMapping("/{id}")
    public Group getById(@PathVariable Long id){
        return groupService.getGroup(id);
    }

    @Operation(summary = "cоздать группу")
    @PostMapping()
    public ResponseEntity<Group> create(@RequestBody Group group){
        try {
            return new ResponseEntity<>(groupService.createGroup(group), HttpStatus.OK);
        } catch (DuplicateDataException exception) {
            return new ResponseEntity<>(new Group(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "обновить группу")
    @PutMapping("/{id}")
    public ResponseEntity<Group> update(@PathVariable Long id,@RequestBody Group group){
        try {
            return new ResponseEntity<>(groupService.updateGroup(id,group), HttpStatus.OK);
        } catch (DuplicateDataException exception) {
            return new ResponseEntity<>(new Group(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "удаление группы")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try {
            groupService.deleteGroup(id);
            return new ResponseEntity<>("Удаление прошло успешно!",HttpStatus.OK);
        } catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Добавление человека в группу")
    @PostMapping("/{group_id}/contacts/{contact_id}")
    public ResponseEntity<Group> addContactToGroup(
            @PathVariable(name = "group_id") Long groupId
            ,@PathVariable(name = "contact_id") Long contactId)
    {
        try {
            return new ResponseEntity<>(groupService.addContactToGroup(groupId,contactId),HttpStatus.OK);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new Group(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Удаление человека из группы")
    @DeleteMapping("/{group_id}/contacts/{contact_id}")
    public ResponseEntity<Group> deleteContactFromGroup(
            @PathVariable(name = "group_id") Long groupId
            ,@PathVariable(name = "contact_id") Long contactId)
    {
        try {
            return new ResponseEntity<>(groupService.deleteContactFromGroup(groupId,contactId),HttpStatus.OK);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new Group(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Отправление сообщения всем пользователям (Перадача инфы сервису нотификаций по кафке)")
    @PostMapping("/{group_id}/notification/{template_id}")
    public void sendNotification(
            @PathVariable(name = "group_id") Long groupId,
            @PathVariable(name = "template_id") Long templateId
    ){
//        kafkaService.send(groupService.getGroup(groupId),notificationTemplateService.getTemplate(templateId));
        kafkaService.send(groupId,templateId);
    }
}
