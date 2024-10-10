package com.example.weatherwear.common.exception.handler;

import java.text.MessageFormat;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
  public void handleNotFound(
      NoHandlerFoundException ex, HttpServletRequest request, HttpServletResponse response) {

    int httpStatus = HttpStatus.NOT_FOUND.value();
    String message = "해당 경로를 찾을 수 없습니다.";
    ExceptionResponse.createExceptionResponse(httpStatus, request, response, message);
  }

  //    405
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public void handleMethodNotAllowed(
      HttpRequestMethodNotSupportedException ex,
      HttpServletRequest request,
      HttpServletResponse response) {

    int httpStatus = HttpStatus.METHOD_NOT_ALLOWED.value();
    String message = "해당 요청을 처리 할 수 없습니다.";
    ExceptionResponse.createExceptionResponse(httpStatus, request, response, message);
  }

  //  해당 예외는 Spring Security에서 처리해야 할 것 같아 주석 처리
  //  //    403
  //  @ExceptionHandler(AccessDeniedException.class)
  //  public ResponseEntity<HttpErrorInfo> handleAccessDeniedException(
  //      AccessDeniedException ex, HttpServletRequest request) {
  //    String message = "접근이 거부되었습니다.";
  //    return createExceptionResponse(HttpStatus.FORBIDDEN, request, message);
  //  }
  //
  //  //    401
  //  @ExceptionHandler(AuthenticationException.class)
  //  public ResponseEntity<HttpErrorInfo> handleAuthenticationException(
  //      AuthenticationException ex, HttpServletRequest request) {
  //    String message = "인증이 필요합니다.";
  //    return createExceptionResponse(HttpStatus.UNAUTHORIZED, request, message);
  //  }

  //    HTTP 요청 매개변수 누락
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public void handlerMissingServletRequestParameterException(
      MissingServletRequestParameterException ex,
      HttpServletRequest request,
      HttpServletResponse response) {

    int httpStatus = HttpStatus.METHOD_NOT_ALLOWED.value();

    String parameterName = ex.getParameterName();
    String message = MessageFormat.format("{0}의 값이 누락되었습니다.", parameterName);

    ExceptionResponse.createExceptionResponse(httpStatus, request, response, message);
  }

  //    유효성 검사 실패
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public void handlerMethodArgumentNotValidException(
      MethodArgumentNotValidException ex,
      HttpServletRequest request,
      HttpServletResponse response) {

    int httpStatus = HttpStatus.BAD_REQUEST.value();

    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    String message =
        fieldErrors.stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .toString();

    ExceptionResponse.createExceptionResponse(httpStatus, request, response, message);
  }

  //    해당 데이터를 조회 할 수 없음
  @ExceptionHandler(NotFoundDataException.class)
  public void handlerNotFoundDataException(
      NotFoundDataException ex, HttpServletRequest request, HttpServletResponse response) {

    int httpStatus = HttpStatus.BAD_REQUEST.value();
    String message = ex.getMessage();
    ExceptionResponse.createExceptionResponse(httpStatus, request, response, message);
  }

  //    DB 접근 예외
  @ExceptionHandler(DataAccessException.class)
  public void handleDataAccessException(
      DataAccessException ex, HttpServletRequest request, HttpServletResponse response) {

    int httpStatus = HttpStatus.BAD_REQUEST.value();
    String message = "DB 접근 예외가 발생했습니다.";
    ExceptionResponse.createExceptionResponse(httpStatus, request, response, message);
  }

  //    DB 무결성 재약 조건 위배
  @ExceptionHandler(DataIntegrityViolationException.class)
  public void handlerDataIntegrityViolationException(
      DataIntegrityViolationException ex,
      HttpServletRequest request,
      HttpServletResponse response) {

    int httpStatus = HttpStatus.BAD_REQUEST.value();
    String message = ex.getMessage();
    ExceptionResponse.createExceptionResponse(httpStatus, request, response, message);
  }

  //    모든 예외 처리
  @ExceptionHandler(Exception.class)
  public void handleGenericException(
      Exception ex, HttpServletRequest request, HttpServletResponse response) {
    int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    String message = "알 수 없는 오류가 발생했습니다.";
    ExceptionResponse.createExceptionResponse(httpStatus, request, response, message);
  }
}
