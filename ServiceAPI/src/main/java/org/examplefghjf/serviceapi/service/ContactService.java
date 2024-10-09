package org.examplefghjf.serviceapi.service;

import org.examplefghjf.serviceapi.db.entity.Contact;
import org.examplefghjf.serviceapi.db.repository.ContactRepository;
import org.examplefghjf.serviceapi.exception.DuplicateDataException;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Contact getContactById(Long id){
        return contactRepository.findById(id).orElseThrow();
    }

    public Contact createContact(Contact contact) throws DuplicateDataException , IllegalArgumentException{
        if (contact.getId()!=null) throw new IllegalArgumentException("В теле запроса не должно быть id!");
        try {
            return contactRepository.save(contact);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateDataException("Уникальность каких то данных была нарушена!");
        }
    }

    public Contact updateContact(Long id,Contact contact) throws NoSuchElementException {
        Contact existedContact = contactRepository.findById(id).orElseThrow();
        existedContact.setContactName(contact.getContactName());
        existedContact.setEmail(contact.getEmail());
        existedContact.setPhone(contact.getPhone());
        existedContact.setStatus(contact.getStatus());

        contactRepository.save(existedContact);
        return existedContact;
    }

    public void deleteContact(Long id) throws RuntimeException {
        if (contactRepository.findById(id).orElse(null)==null) throw new RuntimeException("нет такого значения");
        contactRepository.deleteById(id);
    }
}
