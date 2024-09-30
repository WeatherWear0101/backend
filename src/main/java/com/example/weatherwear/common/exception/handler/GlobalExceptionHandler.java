package com.example.weatherwear.common.exception.handler;

import java.nio.file.AccessDeniedException;
import java.text.MessageFormat;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.weatherwear.common.exception.NotFoundDataException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  //    404
  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<HttpErrorInfo> handleNotFound(
      NoHandlerFoundException ex, HttpServletRequest request) {
    String message = "해당 경로를 찾을 수 없습니다.";
    return createExceptionResponse(HttpStatus.NOT_FOUND, request, message);
  }

  //    405
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<HttpErrorInfo> handleMethodNotAllowed(
      HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
    String message = "해당 요청을 처리 할 수 없습니다.";
    return createExceptionResponse(HttpStatus.METHOD_NOT_ALLOWED, request, message);
  }

  //    403
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<HttpErrorInfo> handleAccessDeniedException(
      AccessDeniedException ex, HttpServletRequest request) {
    String message = "접근이 거부되었습니다.";
    return createExceptionResponse(HttpStatus.FORBIDDEN, request, message);
  }

  //    401
  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<HttpErrorInfo> handleAuthenticationException(
      AuthenticationException ex, HttpServletRequest request) {
    String message = "인증이 필요합니다.";
    return createExceptionResponse(HttpStatus.UNAUTHORIZED, request, message);
  }

  //    HTTP 요청 매개변수 누락
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<HttpErrorInfo> handlerMissingServletRequestParameterException(
      MissingServletRequestParameterException ex, HttpServletRequest request) {
    String parameterName = ex.getParameterName();
    String message = MessageFormat.format("{0}의 값이 누락되었습니다.", parameterName);

    return createExceptionResponse(HttpStatus.BAD_REQUEST, request, message);
  }

  //    유효성 검사 실패
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<HttpErrorInfo> handlerMethodArgumentNotValidException(
      MethodArgumentNotValidException ex, HttpServletRequest request) {
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    String message =
        fieldErrors.stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .toString();

    return createExceptionResponse(HttpStatus.BAD_REQUEST, request, message);
  }

  //    해당 데이터를 조회 할 수 없음
  @ExceptionHandler(NotFoundDataException.class)
  public ResponseEntity<HttpErrorInfo> handlerNotFoundDataException(
      NotFoundDataException ex, HttpServletRequest request) {
    String message = ex.getMessage();
    return createExceptionResponse(HttpStatus.BAD_REQUEST, request, message);
  }

  //    DB 접근 예외
  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<HttpErrorInfo> handleDataAccessException(
      DataAccessException ex, HttpServletRequest request) {
    String message = "DB 접근 예외가 발생했습니다.";
    return createExceptionResponse(HttpStatus.BAD_REQUEST, request, message);
  }

  //    DB 무결성 재약 조건 위배
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<HttpErrorInfo> handlerDataIntegrityViolationException(
      DataIntegrityViolationException ex, HttpServletRequest request) {
    String message = ex.getMessage();
    return createExceptionResponse(HttpStatus.SERVICE_UNAVAILABLE, request, message);
  }

  //    모든 예외 처리
  @ExceptionHandler(Exception.class)
  public ResponseEntity<HttpErrorInfo> handleGenericException(
      Exception ex, HttpServletRequest request) {
    String message = "알 수 없는 오류가 발생했습니다.";
    return createExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, request, message);
  }

  private ResponseEntity<HttpErrorInfo> createExceptionResponse(
      HttpStatus httpStatus, HttpServletRequest request, String message) {
    final String path = request.getRequestURI();

    log.error("Path: {}, Status: {}, Exception: {}", path, httpStatus.value(), message);
    return ResponseEntity.status(httpStatus).body(new HttpErrorInfo(httpStatus, path, message));
  }
}
