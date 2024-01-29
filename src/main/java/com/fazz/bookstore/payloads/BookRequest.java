package com.fazz.bookstore.payloads;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
public class BookRequest {
  @Schema(description = "Book title", example = "Off The Record 3", requiredMode = RequiredMode.REQUIRED)
  private String title;

  @Schema(description = "ID Author", example = "6e212a7c-61d9-423d-81a7-962c500f6c91", requiredMode = RequiredMode.REQUIRED)
  private String idAuthor;

  @Schema(description = "Book published year", example = "2021", requiredMode = RequiredMode.REQUIRED)
  private Integer year;

  @Schema(description = "Book stock", example = "10")
  private Integer stock;
}
