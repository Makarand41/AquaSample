package com.aquaindica.controller;

import com.aquaindica.Entity.Admin;
import com.aquaindica.config.OtpUtil;
import com.aquaindica.dto.AdminLoginRequest;
import com.aquaindica.dto.OtpRequest;
import com.aquaindica.dto.ReplyDto;
import com.aquaindica.repository.AdminRepository;
import com.aquaindica.service.ContactService;
import com.aquaindica.serviceimpl.EmailService;
import com.aquaindica.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin("*") // allow Angular
public class AdminController {

    private final AdminRepository adminRepository;
    private final OtpUtil otpUtil;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final ContactService contactService;

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody AdminLoginRequest request) {
//        Admin admin = adminRepository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("Invalid username"));
//
//        if (!admin.getPassword().equals(request.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        return ResponseEntity.ok("Login successful");
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginRequest request) {

        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String otp = otpUtil.generateOtp();
        admin.setOtp(otp);
        admin.setOtpExpiry(LocalDateTime.now().plusMinutes(3));
        adminRepository.save(admin);

        emailService.sendOtp(admin.getEmail(), otp);
        return ResponseEntity.ok("OTP sent");
    }
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest request) {

        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (!admin.getOtp().equals(request.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        if (admin.getOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        admin.setOtp(null);
        admin.setOtpExpiry(null);
        adminRepository.save(admin);

        String token = jwtUtil.generateToken(admin.getUsername(),admin.getRole().name());

        emailService.sendLoginNotification(admin.getEmail(),request.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/reply")
    public ResponseEntity<Map<String, String>> sendReply(@RequestBody ReplyDto dto) {
        emailService.sendMsg(dto.getEmail(), dto.getUsername(), dto.getMessage(),dto.getCc());
        return ResponseEntity.ok(Map.of("message", "Email Sent Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok("Contact deleted successfully");
    }

}
