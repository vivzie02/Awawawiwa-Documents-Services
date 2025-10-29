package com.awawawiwa.awawawiwa_documents_services.controller;

import com.awawawiwa.awawawiwa_documents_services.dto.ConfirmationEmailRequestDto;
import com.awawawiwa.awawawiwa_documents_services.dto.EmailRequestDto;
import com.awawawiwa.awawawiwa_documents_services.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDto request) {
        emailService.sendEmail(request);
        return ResponseEntity.ok("Email sent successfully");
    }

    @PostMapping("/sendConfirmationMail")
    public ResponseEntity<String> sendConfirmationMail(@RequestBody ConfirmationEmailRequestDto request){
        emailService.sendConfirmationEmail(request);
        return ResponseEntity.ok("confirmation email sent");
    }
}