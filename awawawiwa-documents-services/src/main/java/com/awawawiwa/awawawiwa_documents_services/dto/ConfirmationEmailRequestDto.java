package com.awawawiwa.awawawiwa_documents_services.dto;

import lombok.Data;

@Data
public class ConfirmationEmailRequestDto {
    private String userId;
    private String recipient;
    private String subject;
    private String body;
    private String token;
}
