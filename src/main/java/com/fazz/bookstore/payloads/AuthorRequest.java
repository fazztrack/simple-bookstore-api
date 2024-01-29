package com.fazz.bookstore.payloads;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;

@Getter
public class AuthorRequest {
  @Schema(description = "Author name", example = "J.K Rowling", requiredMode = RequiredMode.REQUIRED)
  private String authorName;
}
