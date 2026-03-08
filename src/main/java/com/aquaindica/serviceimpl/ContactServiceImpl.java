package com.aquaindica.serviceimpl;


import com.aquaindica.Entity.ContactMessage;
import com.aquaindica.dto.ContactRequest;
import com.aquaindica.repository.ContactRepository;
import com.aquaindica.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public ContactMessage saveMessage(ContactRequest request) {

        ContactMessage message = new ContactMessage();
        message.setFirstName(request.getFirstName());
        message.setLastName(request.getLastName());
        message.setEmail(request.getEmail());
        message.setPhone(request.getPhone());
        message.setSubject(request.getSubject());
        message.setMessage(request.getMessage());
        message.setCreatedAt(LocalDateTime.now());

        return contactRepository.save(message);
    }

//    @Override
//    public List<ContactMessage> getAllMessages() {
//        return contactRepository.findAll();
//    }
    @Override
    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new RuntimeException("Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
    }
@Override
    public void archiveContact(Long id) {
        ContactMessage contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
    contact.setArchived(true);
    contactRepository.save(contact);
    }
    @Override
    public List<ContactMessage> getAllMessages() {
        return  contactRepository.findByArchivedFalse();
    }
@Override
    public List<ContactMessage> getArchivedMessages() {
        return  contactRepository.findByArchivedTrue();
    }
    @Override
    public ContactMessage unarchiveContact(Long id) {
        ContactMessage contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
        contact.setArchived(false);
       return contactRepository.save(contact);
    }
}

