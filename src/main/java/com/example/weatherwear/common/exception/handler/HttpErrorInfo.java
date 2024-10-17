package com.example.weatherwear.common.exception.handler;

import java.io.Serializable;
import java.time.ZonedDateTime;

import lombok.Getter;

@Getter
public class HttpErrorInfo implements Serializable {
  private final String timestamp;
  private final int status;
  private final String error;
  private final String path;

  public HttpErrorInfo(int httpStatus, String path, String message) {
    this.timestamp = ZonedDateTime.now().toString();
    this.status = httpStatus;
    this.path = path;
    this.error = message;
  }
}
