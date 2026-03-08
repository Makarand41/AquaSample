package com.aquaindica.config;

import com.aquaindica.Entity.Admin;
import com.aquaindica.enums.Role;
import com.aquaindica.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class StartupDataLoader implements CommandLineRunner {

    private final AdminRepository adminRepository;
 private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (adminRepository.findByUsername("makarandpatil67@gmail.com").isEmpty()) {
            Admin admin = Admin.builder()
                    .username("makarandpatil67@gmail.com")
                    .password(passwordEncoder.encode("admin123"))
                    .email("makarandpatil67@gmail.com")
                    .role(Role.ADMIN)
                    .build();
            adminRepository.save(admin);
            System.out.println("✅ Default admin created");
        }
    }
}
