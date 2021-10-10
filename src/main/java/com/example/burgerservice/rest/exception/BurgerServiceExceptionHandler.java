package com.example.burgerservice.rest.exception;

import com.example.burgerservice.rest.dto.BaseResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BurgerServiceExceptionHandler {

    @ExceptionHandler(BurgerNotFoundException.class)
    protected ResponseEntity<BaseResponseDto> handleBurgerNotFoundException(
            BurgerNotFoundException burgerNotFoundException) {

        return new ResponseEntity<>(
                new BaseResponseDto(burgerNotFoundException.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<BaseResponseDto> handleRuntimeException(RuntimeException runtimeException) {
        return new ResponseEntity<>(
                new BaseResponseDto(runtimeException.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseResponseDto> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        return new ResponseEntity<>(
                new BaseResponseDto(methodArgumentNotValidException.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
