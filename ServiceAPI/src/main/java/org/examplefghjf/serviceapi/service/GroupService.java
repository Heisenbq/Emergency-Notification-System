package org.examplefghjf.serviceapi.service;

import jakarta.transaction.Transactional;
import org.examplefghjf.serviceapi.db.entity.Contact;
import org.examplefghjf.serviceapi.db.entity.Group;
import org.examplefghjf.serviceapi.db.repository.ContactRepository;
import org.examplefghjf.serviceapi.db.repository.GroupRepository;
import org.examplefghjf.serviceapi.exception.DuplicateDataException;
import org.examplefghjf.serviceapi.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final ContactRepository contactRepository;


    @Autowired
    public GroupService(GroupRepository groupRepository, ContactRepository contactRepository) {
        this.groupRepository = groupRepository;
        this.contactRepository = contactRepository;
    }

    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }

    public Group getGroup(Long id) throws NoSuchElementException {
        return groupRepository.findById(id).orElseThrow();
    }

    public Group createGroup(Group group) throws DuplicateDataException {
        try {
            return groupRepository.save(group);
        }catch (DataIntegrityViolationException e){
            throw new DuplicateDataException("Уникальность каких то данных была нарушена!");
        }
    }

    public Group updateGroup(Long id,Group group) throws NoSuchElementException,DuplicateDataException {
        Group existedGroup = groupRepository.findById(id).orElseThrow();
        existedGroup.setGroupName(group.getGroupName());
        try {
            return groupRepository.save(existedGroup);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateDataException("Уникальность каких то данных была нарушена!");
        }
    }


    public Group addContactToGroup(Long groupId,Long contactId) throws NoSuchElementException{
        Group group = groupRepository.findById(groupId).orElseThrow();
        Contact contact = contactRepository.findById(contactId).orElseThrow();
        group.getContacts().add(contact);
        return groupRepository.save(group);
    }

    public Group deleteContactFromGroup(Long groupId,Long contactId) throws NoSuchElementException{
        Group group = groupRepository.findById(groupId).orElseThrow();
        Contact contact = contactRepository.findById(contactId).orElseThrow();
        group.getContacts().remove(contact);
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) throws  NoSuchElementException{
        groupRepository.findById(id).orElseThrow();
        groupRepository.deleteById(id);
    }
}
