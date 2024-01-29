package com.fazz.bookstore.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResponseData<T> extends Response {
  private T data;

  public ResponseData(String message, Boolean success, T data) {
    super(message, success);
    this.data = data;
  }
}
