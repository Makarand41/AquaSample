package com.aquaindica.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String subject;

    @Column(length = 1000)
    private String message;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private boolean archived = false;
}
