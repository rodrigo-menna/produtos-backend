package com.example.gerenciaprodutos.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionMessageDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ExceptionMessageDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status((HttpStatus.BAD_REQUEST.value()))
                        .title("Method Argument Not Valid Exception")
                        .details("Check the fields erros")
                        .fields(fields.toUpperCase())
                        .fieldsMessage(fieldsMessage)
                        .developerMessage(e.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionMessageDetails> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(
                ExceptionMessageDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status((HttpStatus.BAD_REQUEST.value()))
                        .title("Bad Request Exception")
                        .details(e.getMessage())
                        .developerMessage(e.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionMessageDetails> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(
                ExceptionMessageDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status((HttpStatus.NOT_FOUND.value()))
                        .title("Not Found Exception")
                        .details("Entity or Field not Found")
                        .details(e.getMessage())
                        .developerMessage(e.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
