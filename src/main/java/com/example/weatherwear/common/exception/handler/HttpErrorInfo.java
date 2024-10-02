package com.example.weatherwear.common.exception.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
public class HttpErrorInfo {
    private final ZonedDateTime timestamp;
    private final int status;
    private final String error;
    private final String path;

    public HttpErrorInfo(HttpStatus httpStatus, String path, String message) {
        timestamp = ZonedDateTime.now();
        this.status = httpStatus.value();
        this.path = path;
        this.error = message;
    }
}
