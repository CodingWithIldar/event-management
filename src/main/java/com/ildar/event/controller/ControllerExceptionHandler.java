package com.ildar.event.controller;

import com.ildar.event.dto.ErrorResponseDTO;
import com.ildar.event.exception.EventRegistrationExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EventRegistrationExistsException.class)
    public ResponseEntity<ErrorResponseDTO> processEventRegistrationExists(EventRegistrationExistsException exc) {
        return ResponseEntity.internalServerError()
                .body(ErrorResponseDTO.builder()
                        .errorMessage(exc.getMessage()).build());
    }
}
