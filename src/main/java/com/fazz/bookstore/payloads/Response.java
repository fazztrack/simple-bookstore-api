package com.fazz.bookstore.payloads;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
  @Schema(example = "Message Response or Error Message")
  private String message;

  @Schema(example = "true")
  private Boolean success;
}
