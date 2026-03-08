package com.aquaindica.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void sendOtp(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Admin Login OTP");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);
    }
    public void sendLoginNotification(String to,String username){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Log in successfully ");
        message.setText("You log in successfully with user name  " + username);
        mailSender.send(message);

    }
    public void sendMsg(String to, String username, String msg,String cc) {
        SimpleMailMessage message = new SimpleMailMessage();
        if (cc != null && !cc.isEmpty()) {
            message.setCc(cc);
        }

        message.setTo(to);
        message.setSubject("Reply from Aquaindica Admin");
        message.setText("Hello " + username + ",\n\n" + msg + "\n\nRegards,\nAquaindica Team");
        mailSender.send(message);
    }

}
