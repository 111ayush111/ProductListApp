package com.bitsandbytes.product.exception;

import com.bitsandbytes.product.dto.CategoryDTO;
import com.bitsandbytes.product.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


//    @ExceptionHandler---->If this specific exception is thrown anywhere in controllers, call this method.
    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex,WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(request.getDescription(false),
                HttpStatus.CONFLICT,
                ex.getMessage(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponseDTO);
//        ResponseEntity.status(HttpStatus.CONFLICT)--------->500 status code
//        and(body(ex.getMessage()))-----------> response should be message part of error
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCategoryNotFoundException(CategoryNotFoundException ex,WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(request.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponseDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleGlobalException(Exception ex,WebRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponseDTO);
    }
}
 