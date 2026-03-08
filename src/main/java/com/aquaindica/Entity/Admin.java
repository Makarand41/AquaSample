package com.aquaindica.Entity;

import com.aquaindica.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;
    private String email;

    private String otp;
    private LocalDateTime otpExpiry;
    @Enumerated(EnumType.STRING)
    private Role role;
}
