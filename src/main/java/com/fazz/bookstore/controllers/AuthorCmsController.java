package com.fazz.bookstore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fazz.bookstore.models.Author;
import com.fazz.bookstore.payloads.AuthorRequest;
import com.fazz.bookstore.payloads.Response;
import com.fazz.bookstore.payloads.ResponseData;
import com.fazz.bookstore.payloads.ResponseError;
import com.fazz.bookstore.payloads.ResponseMeta;
import com.fazz.bookstore.services.author.AuthorInterface;
import com.fazz.bookstore.utils.ResponseHandler;
import com.fazz.bookstore.utils.SortUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cms/authors")
@Tag(name = "Authors CMS", description = "CMS Author API")
public class AuthorCmsController {
  @Autowired
  AuthorInterface authorInterface;

  @Operation(summary = "API for create author", description = "Return author created", responses = {
      @ApiResponse(responseCode = "201", content = {
          @Content(schema = @Schema(implementation = ResponseData.class)) }, description = "Response OK"),
      @ApiResponse(responseCode = "500", content = {
          @Content(schema = @Schema(implementation = ResponseError.class)) }, description = "Response Internal Server Error")
  })
  @PostMapping
  public ResponseEntity<?> createAuthor(@RequestBody AuthorRequest request) {
    try {
      ResponseData<Author> authorRes = authorInterface.postAuthor(request);
      return ResponseHandler.response(201, true, authorRes.getMessage(), authorRes.getData());
    } catch (Exception e) {
      throw e;
    }
  }

  @Operation(summary = "API for fetch all author", description = "Return all author", responses = {
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = ResponseMeta.class))
      }, description = "Response OK"),
      @ApiResponse(responseCode = "500", content = {
          @Content(schema = @Schema(implementation = ResponseError.class))
      }, description = "Response Internal Server Error")
  })
  @GetMapping
  public ResponseEntity<?> fetchAuthor(@RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "5") Integer limit, @RequestParam(defaultValue = "createdAt,desc") String[] sort) {
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

      ResponseMeta<List<Author>> authorRes = authorInterface.getAuthors(true, pageable);
      return ResponseHandler.response(200, authorRes.getSuccess(), authorRes.getMessage(), authorRes.getData(),
          authorRes.getMeta());
    } catch (Exception e) {
      throw e;
    }
  }

  @Operation(summary = "API for update author by id", description = "Return author updated", responses = {
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = ResponseData.class))
      }, description = "Response OK"),
      @ApiResponse(responseCode = "500", content = {
          @Content(schema = @Schema(implementation = ResponseError.class))
      }, description = "Response Internal Server Error"),
      @ApiResponse(responseCode = "404", content = {
          @Content(schema = @Schema(implementation = ResponseError.class))
      }, description = "Response author not found!")
  })
  @PutMapping("/{id}")
  public ResponseEntity<?> updateAuthor(@PathVariable String id, @RequestBody AuthorRequest request) {
    try {
      ResponseData<Author> authorRes = authorInterface.putAuthor(id, request);

      return ResponseHandler.response(200, authorRes.getSuccess(), authorRes.getMessage(), authorRes.getData());
    } catch (Exception e) {
      throw e;
    }
  }

  @Operation(summary = "API for delete author by id", description = "Not returning data", responses = {
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = ResponseData.class))
      }, description = "Response OK"),
      @ApiResponse(responseCode = "500", content = {
          @Content(schema = @Schema(implementation = ResponseError.class))
      }, description = "Response Internal Server Error"),
      @ApiResponse(responseCode = "404", content = {
          @Content(schema = @Schema(implementation = ResponseError.class))
      }, description = "Response author not found!")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBook(@PathVariable String id) {
    try {
      Response res = authorInterface.deleteAuthor(id);

      return ResponseHandler.response(200, res.getSuccess(), res.getMessage());
    } catch (Exception e) {
      throw e;
    }
  }
}
