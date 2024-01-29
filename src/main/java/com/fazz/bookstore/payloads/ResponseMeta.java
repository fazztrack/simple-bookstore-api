package com.fazz.bookstore.payloads;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseMeta<T> extends Response {
  @Schema(subTypes = List.class)
  private T data;
  private Meta meta;

  public ResponseMeta(String message, Boolean success, T data, Meta meta) {
    super(message, success);
    this.data = data;
    this.meta = meta;
  }
}
