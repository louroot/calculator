package com.interest.calculator.api.health;

import org.springframework.http.HttpStatus;

public enum Status {
    OK("ok",HttpStatus.OK),
    CRITICAL("critical", HttpStatus.SERVICE_UNAVAILABLE);
    
    private String message;
    private HttpStatus httpStatus;
    
    Status(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
