package com.fazz.bookstore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fazz.bookstore.models.Book;
import com.fazz.bookstore.payloads.ResponseData;
import com.fazz.bookstore.payloads.ResponseError;
import com.fazz.bookstore.payloads.ResponseMeta;
import com.fazz.bookstore.services.book.BookInterface;
import com.fazz.bookstore.utils.ResponseHandler;
import com.fazz.bookstore.utils.SortUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "Books API")
public class BookController {
  @Autowired
  BookInterface bookInterface;

  @Operation(summary = "API for fetch all active books", description = "Return all books", responses = {
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = ResponseMeta.class))
      }, description = "Response OK"),
      @ApiResponse(responseCode = "500", content = {
          @Content(schema = @Schema(implementation = ResponseError.class))
      }, description = "Response Internal Server Error")
  })
  @GetMapping
  public ResponseEntity<?> fetchBook(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "5") Integer limit,
      @RequestParam(defaultValue = "createdAt,desc") String[] sort) {
    try {
      List<Order> orders = new ArrayList<>();

      if (sort[0].contains(",")) {
        // multiple sort
        for (String sortOrder : sort) {
          String[] _sort = sortOrder.split(",");
          orders.add(new Order(SortUtil.getSortDirection(_sort[1]), _sort[0]));
        }
      } else {
        // single sort
        orders.add(new Order(SortUtil.getSortDirection(sort[1]), sort[0]));
      }

      Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(orders));

      ResponseMeta<List<Book>> booksRes = bookInterface.getBooks(false, pageable);
      return ResponseHandler.<List<Book>>response(200, booksRes.getSuccess(), booksRes.getMessage(), booksRes.getData(),
          booksRes.getMeta());
    } catch (Exception e) {
      throw e;
    }
  }

  @Operation(summary = "API for fetch book by id", description = "Return book per id", responses = {
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = ResponseData.class))
      }, description = "Response OK"),
      @ApiResponse(responseCode = "500", content = {
          @Content(schema = @Schema(implementation = ResponseError.class))
      }, description = "Response Internal Server Error"),
      @ApiResponse(responseCode = "404", content = {
          @Content(schema = @Schema(implementation = ResponseError.class))
      }, description = "Response book not found!")
  })
  @GetMapping("/{id}")
  public ResponseEntity<?> fetchBookById(@PathVariable String id) {
    try {
      ResponseData<Book> bookRes = bookInterface.getBookById(id);
      return ResponseHandler.response(200, bookRes.getSuccess(), bookRes.getMessage(), bookRes.getData());
    } catch (Exception e) {
      throw e;
    }
  }
}
