package com.fazz.bookstore.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseError<T> extends Response {
  private T error;

  public ResponseError(String message, T error) {
    super(message, false);
    this.error = error;
  }
}
