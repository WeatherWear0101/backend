package com.example.weatherwear.common.exception.handler;

import com.example.weatherwear.common.exception.NotFoundDataException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

//    404
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<HttpErrorInfo> handleNotFound(NoHandlerFoundException ex, HttpServletRequest request) {
        String message = "해당 경로를 찾을 수 없습니다.";
        return createExceptionResponse(HttpStatus.NOT_FOUND, request, message);
    }

//    405
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpErrorInfo> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        String message = "해당 요청을 처리 할 수 없습니다.";
        return createExceptionResponse(HttpStatus.METHOD_NOT_ALLOWED, request, message);
    }

//    유효성 검사 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpErrorInfo> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream()
                .map(error -> error.getField() + ", " + error.getDefaultMessage())
                .toString();

        return createExceptionResponse(HttpStatus.BAD_REQUEST, request, message);
    }

//    해당 데이터를 조회 할 수 없음
    @ExceptionHandler(NotFoundDataException.class)
    public ResponseEntity<HttpErrorInfo> handlerNotFoundDataException(NotFoundDataException ex, HttpServletRequest request) {
        String message = ex.getMessage();
        return createExceptionResponse(HttpStatus.BAD_REQUEST, request, message);
    }

//    DB 접근 예외
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<HttpErrorInfo>  handleDataAccessException(DataAccessException ex, HttpServletRequest request) {
        String message = "DB 접근 예외가 발생했습니다.";
        return createExceptionResponse(HttpStatus.BAD_REQUEST, request, message);
    }

//    DB 무결성 재약 조건 위배
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<HttpErrorInfo> handlerDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        String message = ex.getMessage();
        return createExceptionResponse(HttpStatus.SERVICE_UNAVAILABLE, request, message);
    }

    private ResponseEntity<HttpErrorInfo> createExceptionResponse(HttpStatus httpStatus, HttpServletRequest request, String message) {
        final String path = request.getRequestURI();

        log.error("Path: {}, Status: {}, Exception: {}", path, httpStatus.value(), message);
        return ResponseEntity.status(httpStatus).body(new HttpErrorInfo(httpStatus, path, message));
    }
}