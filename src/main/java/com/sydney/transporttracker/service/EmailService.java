package com.sydney.transporttracker.service;

import com.sydney.transporttracker.model.Alert;
import com.sydney.transporttracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;
    public void sendEmail(String recipient, String subject, String body) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipient);
            email.setSubject(subject);
            email.setText(body);
            javaMailSender.send(email);
        } catch (MailException e) {
            System.out.println("Failed to send email : " + e.getMessage());
        }
    }
}
