package com.awawawiwa.awawawiwa_documents_services.service.impl;

import com.awawawiwa.awawawiwa_documents_services.constants.DocumentsConstants;
import com.awawawiwa.awawawiwa_documents_services.dto.ConfirmationEmailRequestDto;
import com.awawawiwa.awawawiwa_documents_services.dto.EmailRequestDto;
import com.awawawiwa.awawawiwa_documents_services.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import static com.awawawiwa.awawawiwa_documents_services.constants.DocumentsConstants.confirmationEmailTemplate;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail(EmailRequestDto request){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(request.getRecipient());
        message.setSubject(request.getSubject());
        message.setText(request.getBody());
        mailSender.send(message);
    }

    @Override
    public void sendConfirmationEmail(ConfirmationEmailRequestDto requestDto) {
        // Example confirmation URL (frontend endpoint or API)
        var token = requestDto.getToken();
        String confirmationUrl = DocumentsConstants.confirmationEmailUrl + token;

        // Build email content using Thymeleaf
        Context context = new Context();
        context.setVariable("confirmationUrl", confirmationUrl);

        String htmlContent = templateEngine.process(confirmationEmailTemplate, context);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(requestDto.getRecipient());
            helper.setSubject("Confirm your email address");
            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send confirmation email", e);
        }
    }

}
