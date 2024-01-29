package com.fazz.bookstore.utils;

import org.springframework.http.ResponseEntity;

import com.fazz.bookstore.payloads.Meta;
import com.fazz.bookstore.payloads.Response;
import com.fazz.bookstore.payloads.ResponseData;
import com.fazz.bookstore.payloads.ResponseError;
import com.fazz.bookstore.payloads.ResponseMeta;

public class ResponseHandler {
  public static ResponseEntity<Response> response(Integer statusCode, Boolean isSuccess, String message) {
    Response body = new Response(message, isSuccess);

    return ResponseEntity.status(statusCode).body(body);
  }

  public static <T> ResponseEntity<?> response(Integer statusCode, Boolean isSuccess, String message, T result) {
    if (statusCode >= 400 && !isSuccess) {
      ResponseError<T> body = new ResponseError<T>(message, result);
      return ResponseEntity.status(statusCode).body(body);
    }

    ResponseData<T> body = new ResponseData<T>(message, isSuccess, result);
    return ResponseEntity.status(statusCode).body(body);
  }

  public static <T> ResponseEntity<?> response(Integer statusCode, Boolean isSuccess, String message, T result,
      Meta meta) {
    if (statusCode >= 400 && !isSuccess) {
      ResponseError<T> body = new ResponseError<T>(message, result);
      return ResponseEntity.status(statusCode).body(body);
    }

    ResponseMeta<T> body = new ResponseMeta<T>(message, isSuccess, result, meta);
    return ResponseEntity.status(statusCode).body(body);
  }
}
