package com.example.weatherwear.common.exception.handler;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionResponse {
  /** 예외 반환 매서드 */
  public static void createExceptionResponse(
      int httpStatus, HttpServletRequest request, HttpServletResponse response, String message) {
    final String path = request.getRequestURI();
    log.error("Path: {}, Status: {}, Exception: {}", path, httpStatus, message);

    final HttpErrorInfo errorInfo = new HttpErrorInfo(httpStatus, path, message);
    final ObjectMapper mapper = new ObjectMapper();

    try {
      response.setContentType("application/json; charset=UTF-8");
      response.getWriter().write(mapper.writeValueAsString(errorInfo));
      response.setStatus(httpStatus);
    } catch (IOException e) {
      log.error("응답 작성 실패: {}", e.getMessage(), e);
    }
  }
}
