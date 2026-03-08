package com.aquaindica.controller;

import com.aquaindica.Entity.ContactMessage;
import com.aquaindica.dto.ContactRequest;
import com.aquaindica.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<ContactMessage> submitContact(
            @RequestBody ContactRequest request) {

        return ResponseEntity.ok(contactService.saveMessage(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ContactMessage>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllMessages());
    }

    @PutMapping("/archive/{id}")
    public ResponseEntity<String> archiveContact(@PathVariable Long id) {
        contactService.archiveContact(id);
        return ResponseEntity.ok("Contact archived successfully");
    }
    @GetMapping("/archived")
    public ResponseEntity<List<ContactMessage>> getArchived() {
        return ResponseEntity.ok(contactService.getArchivedMessages());
    }

    @PutMapping("/unarchive/{id}")
    public ResponseEntity<?> unarchive(@PathVariable Long id) {
      //  contactService.unarchiveContact(id);
        return ResponseEntity.ok(contactService.unarchiveContact(id));
    }
}
