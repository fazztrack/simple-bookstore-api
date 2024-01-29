package com.fazz.bookstore.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fazz.bookstore.utils.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<?> globalEx(Exception e) {
    log.info("Global Exception Handler Invoked! ", e.getMessage());
    return ResponseHandler.response(500, false, HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getStackTrace());
  }

  @ExceptionHandler(value = NoSuchElementException.class)
  public ResponseEntity<?> nullEx(NoSuchElementException e) {
    log.info("Global Exception Handler Invoked! ", e.getMessage());
    return ResponseHandler.response(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.toString(),
        e.getMessage());
  }
}
