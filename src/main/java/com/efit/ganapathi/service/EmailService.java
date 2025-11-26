package com.efit.ganapathi.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtpEmail(String to, String subject, String otp) throws MessagingException, IOException {

        // Load HTML template
        ClassPathResource resource = new ClassPathResource("templates/otp-template.html");
        String html = new String(Files.readAllBytes(resource.getFile().toPath()));

        // Replace OTP placeholder
        html = html.replace("{{OTP}}", otp);

        // Prepare email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        
        helper.setFrom("dhinesh@whydigit.in");  // IMPORTANT
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(html, true);  // HTML enabled

        // Send email
        mailSender.send(message);
    }
}
