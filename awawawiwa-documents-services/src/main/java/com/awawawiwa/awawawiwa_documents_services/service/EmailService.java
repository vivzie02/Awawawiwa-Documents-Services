package com.awawawiwa.awawawiwa_documents_services.service;

import com.awawawiwa.awawawiwa_documents_services.dto.ConfirmationEmailRequestDto;
import com.awawawiwa.awawawiwa_documents_services.dto.EmailRequestDto;

public interface EmailService {
    void sendEmail(EmailRequestDto request);

    void sendConfirmationEmail(ConfirmationEmailRequestDto requestDto);
}
