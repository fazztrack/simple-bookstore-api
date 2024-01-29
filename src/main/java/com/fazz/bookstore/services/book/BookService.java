package com.fazz.bookstore.services.book;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fazz.bookstore.dao.AuthorRepository;
import com.fazz.bookstore.dao.BookRepository;
import com.fazz.bookstore.models.Author;
import com.fazz.bookstore.models.Book;
import com.fazz.bookstore.payloads.BookRequest;
import com.fazz.bookstore.payloads.Meta;
import com.fazz.bookstore.payloads.Response;
import com.fazz.bookstore.payloads.ResponseData;
import com.fazz.bookstore.payloads.ResponseMeta;

@Service
public class BookService implements BookInterface {
  @Autowired
  AuthorRepository authorRepository;

  @Autowired
  BookRepository bookRepository;

  @Override
  public ResponseData<Book> postBook(BookRequest request) {
    try {
      // find author
      Author author = authorRepository.findById(request.getIdAuthor())
          .orElseThrow(() -> new NoSuchElementException("id author " + request.getIdAuthor() + " is not found!"));

      Book book = new Book(request.getTitle(), author, request.getYear(), request.getStock());

      bookRepository.save(book);

      return new ResponseData<Book>(HttpStatus.valueOf(201).toString(), true, book);
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public ResponseMeta<List<Book>> getBooks(Boolean cms, Pageable pageable) {
    try {
      Page<Book> booksPage = cms ? bookRepository.findAll(pageable) : bookRepository.findByStatusTrue(pageable);

      List<Book> books = booksPage.getContent();

      Meta meta = new Meta(booksPage.getNumber() + 1, (booksPage.getNumber() < 1) ? null : booksPage.getNumber(),
          ((booksPage.getNumber() + 2) <= booksPage.getTotalPages()) ? (booksPage.getNumber() + 2) : null,
          booksPage.getTotalElements());

      return new ResponseMeta<List<Book>>(HttpStatus.OK.toString(), true, books, meta);
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public ResponseData<Book> putBook(String id, BookRequest request) {
    try {
      // find book
      Book book = bookRepository.findById(id)
          .orElseThrow(() -> new NoSuchElementException("id " + id + " is not found!"));

      // find author
      Author author = authorRepository.findById(request.getIdAuthor())
          .orElseThrow(() -> new NoSuchElementException("id author " + request.getIdAuthor() + " is not found!"));

      book.setAuthor(author);
      book.setStock(request.getStock());
      book.setTitle(request.getTitle());
      book.setYear(request.getYear());

      bookRepository.save(book);

      return new ResponseData<Book>(HttpStatus.OK.toString(), true, book);
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public Response deleteBook(String id) {
    try {
      // find book
      Book book = bookRepository.findById(id)
          .orElseThrow(() -> new NoSuchElementException("id " + id + " is not found!"));

      book.setStatus(false);
      bookRepository.save(book);

      return new Response(HttpStatus.OK.toString(), true);
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public ResponseData<Book> getBookById(String id) {
    try {
      // find book
      Book book = bookRepository.findById(id)
          .orElseThrow(() -> new NoSuchElementException("id " + id + " is not found!"));

      return new ResponseData<Book>(HttpStatus.OK.toString(), true, book);
    } catch (Exception e) {
      throw e;
    }
  }

}
