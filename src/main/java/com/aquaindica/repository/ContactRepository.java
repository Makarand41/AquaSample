package com.aquaindica.repository;


import com.aquaindica.Entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactMessage, Long> {
    List<ContactMessage> findByArchivedFalse();
    List<ContactMessage> findByArchivedTrue();

}
