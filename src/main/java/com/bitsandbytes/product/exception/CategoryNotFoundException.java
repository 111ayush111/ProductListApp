package com.bitsandbytes.product.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CategoryNotFoundException extends RuntimeException {
//    constructor ---->called when class is called
    public CategoryNotFoundException(String message) {
        super(message);
    }

}
