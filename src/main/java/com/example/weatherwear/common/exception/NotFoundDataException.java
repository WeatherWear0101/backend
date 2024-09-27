package com.example.weatherwear.common.exception;

/**
 * 해당 데이터를 조회 할 수 없음
 */
public class NotFoundDataException extends RuntimeException {
    public NotFoundDataException(String message) {
        super(message);
    }
}
