package com.ddinnovations.loadsystem.infrastructure.adapters.mail.service;


import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.enums.EmailTemplate;
import com.ddinnovations.loadsystem.infrastructure.adapters.mail.templates.Message;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Value("${load-system.email.sender}")
    private String emailSender;
    private final JavaMailSender mailSender;

    @Async
    public void sendEmailWelcome(String firstName, String token, String email, EmailTemplate emailTemplate) {
        MimeMessage message = mailSender.createMimeMessage();

        String template = Message.welcome(firstName, token, emailTemplate);
        try {
            message.setSubject("Recuperación de contraseña");
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailSender);
            helper.setTo(email);
            helper.setText(template, true);
            mailSender.send(message);
        } catch (Exception e) {
            throw new BusinessException(BusinessException.Type.ERROR_BD);
        }

    }
}
