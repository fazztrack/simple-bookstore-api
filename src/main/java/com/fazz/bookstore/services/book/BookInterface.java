package com.fazz.bookstore.services.book;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fazz.bookstore.models.Book;
import com.fazz.bookstore.payloads.BookRequest;
import com.fazz.bookstore.payloads.Response;
import com.fazz.bookstore.payloads.ResponseData;
import com.fazz.bookstore.payloads.ResponseMeta;

public interface BookInterface {
  ResponseData<Book> postBook(BookRequest request);

  ResponseMeta<List<Book>> getBooks(Boolean cms, Pageable pageable);

  ResponseData<Book> putBook(String id, BookRequest request);

  Response deleteBook(String id);

  ResponseData<Book> getBookById(String id);
}
