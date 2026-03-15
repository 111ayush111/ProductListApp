package com.bitsandbytes.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

//why we need this?------>we need this because we want detailed exception message
@Data
@AllArgsConstructor
public class ExceptionResponseDTO {
//    what message do we need
        private String apipath;
        private String errorMessage;
        private HttpStatus httpStatus;
        private LocalDateTime errorTime;

    public ExceptionResponseDTO(String description, HttpStatus httpStatus, String message, LocalDateTime now) {
        this.errorMessage = message;
        this.httpStatus = httpStatus;
        this.errorTime = now;
        this.apipath = description;
    }
}
