package com.aquaindica.service;


import com.aquaindica.Entity.ContactMessage;
import com.aquaindica.dto.ContactRequest;

import java.util.List;

public interface ContactService {
    ContactMessage saveMessage(ContactRequest request);
    List<ContactMessage> getAllMessages();
    public void deleteContact(Long id);
    public void archiveContact(Long id);
    public List<ContactMessage> getArchivedMessages();
    public ContactMessage unarchiveContact(Long id);
}
