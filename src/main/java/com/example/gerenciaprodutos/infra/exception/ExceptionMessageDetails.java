package com.example.gerenciaprodutos.infra.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionMessageDetails {

    private String title;
    private int status;
    private String details;
    private String developerMessage;
    private String fields;
    private String fieldsMessage;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
}
