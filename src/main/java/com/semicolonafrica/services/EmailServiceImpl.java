package com.semicolonafrica.services;

import com.semicolonafrica.dtos.request.SendEmailRequest;
import com.semicolonafrica.dtos.response.SendEmailResponse;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service

public class EmailServiceImpl{
    @Autowired
    private JavaMailSender mailSender;


    public void simpleEmail(String to, String name) {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(to);
//            message.setSubject("Welcome to our platform");
//            message.setText("Thank you for joining.We're excited to have you on board!");
//            message.setFrom("adedamola1759@gmail.com");
//            mailSender.send(message);
        String subject = "Welcome to Fashion Artisan Connect!";
        String body = "<h1>Hello " + name + ",</h1>"
                + "<p>Thank you for registering on our platform.</p>"
                + "<p>We are excited to connect you with fashion artisans!</p>"
                + "<br><p>Best Regards,<br>Fashion Artisan Connect Team</p>";

        sendEmail(to, subject, body);

    }
    private void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }



//    public void sendPaymentConfirmationEmail(String to, BigDecimal amount, Long jobId) {
//        SendEmailRequest request = new SendEmailRequest();
//        request.setTo(to);
//        request.setSubject("Payment Confirmation");
//        request.setText("Your payment of $" + amount + " for job #" + jobId + " has been successfully processed.");
//        sendSimpleEmail(request);
//    }
}
