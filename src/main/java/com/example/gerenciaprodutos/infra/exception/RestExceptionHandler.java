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
    public ResponseEntity<ExceptionMessageDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ExceptionMessageDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status((HttpStatus.BAD_REQUEST.value()))
                        .title("Bad Request Exception")
                        .details("Check the fields erros")
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .developerMessage(exception.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionMessageDetails> handleBadRequestException(BadRequestException bre) {
        return new ResponseEntity<>(
                ExceptionMessageDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status((HttpStatus.BAD_REQUEST.value()))
                        .title("Bad Request Exception")
                        .details(bre.getMessage())
                        .developerMessage(bre.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionMessageDetails> handleEntityNotFoundException(EntityNotFoundException exception) {
        return new ResponseEntity<>(
                ExceptionMessageDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status((HttpStatus.BAD_REQUEST.value()))
                        .title("Bad Request Exception")
                        .details("Entity or Field not Found")
                        .developerMessage(exception.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
