package org.example.notificationrebalancer.service;

import org.example.notificationrebalancer.db.entity.Contact;
import org.example.notificationrebalancer.db.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    public Contact getContactById(Long id) throws NoSuchElementException{
        return contactRepository.findById(id).orElseThrow();
    }

}

