package com.awawawiwa.awawawiwa_documents_services.dto;

import lombok.Data;

@Data
public class EmailRequestDto {
    private String userId;
    private String recipient;
    private String subject;
    private String body;
}
