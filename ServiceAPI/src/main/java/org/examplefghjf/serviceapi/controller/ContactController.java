package org.examplefghjf.serviceapi.controller;

import org.examplefghjf.serviceapi.db.entity.Contact;
import org.examplefghjf.serviceapi.exception.DuplicateDataException;
import org.examplefghjf.serviceapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/contact")
public class ContactController {
    private final ContactService contactService;


    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping()
    public ResponseEntity<List<Contact>> getAllContacts(){
        return new ResponseEntity<>(contactService.getAllContacts(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Long id){
        return new ResponseEntity<>(contactService.getContactById(id),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact){

        try {
            return new ResponseEntity<>(contactService.createContact(contact), HttpStatus.CREATED);
        } catch (DuplicateDataException exception) {
            return new ResponseEntity<>(new Contact(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id,@RequestBody Contact contact){
        try {
            return new ResponseEntity<>(contactService.updateContact(id,contact),HttpStatus.OK);
        } catch (NoSuchElementException exception){
            return new ResponseEntity<>(new Contact(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id){
        try {
            contactService.deleteContact(id);
            return new ResponseEntity<>("Удаление прошло успешно!",HttpStatus.OK);
        } catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage().toString(),HttpStatus.BAD_REQUEST);
        }
    }
}
