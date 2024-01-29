package com.fazz.bookstore.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meta {
  private Integer current;
  private Integer prev;
  private Integer next;
  private Long totalItem;
}
