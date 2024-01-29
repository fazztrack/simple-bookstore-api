package com.fazz.bookstore.utils;

import org.springframework.data.domain.Sort;

public class SortUtil {
  public static Sort.Direction getSortDirection(String direction) {
    if (direction.equals("asc")) {
      return Sort.Direction.ASC;
    } else if (direction.equals("desc")) {
      return Sort.Direction.DESC;
    }

    return Sort.Direction.DESC;
  }
}