package com.fazz.bookstore.seeders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.fazz.bookstore.dao.AuthorRepository;
import com.fazz.bookstore.dao.BookRepository;
import com.fazz.bookstore.models.Author;
import com.fazz.bookstore.models.Book;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DatabaseSeeder {
  @Autowired
  AuthorRepository authorRepository;

  @Autowired
  BookRepository bookRepository;

  @Autowired
  JdbcTemplate jdbcTemplate;

  @EventListener
  public void seed(ContextRefreshedEvent event) {
    seedAuthorTable();
    seedBookTable();
  }

  private void seedAuthorTable() {
    List<Author> authors = authorRepository.findAll();
    if (authors.size() <= 0) {
      Author a1 = new Author("Pidi Baiq");
      Author a2 = new Author("Ria Sukma Wijaya");
      Author a3 = new Author("Andrea Hirata");

      authors = new ArrayList<>(Arrays.asList(a1, a2, a3));
      for (Author author : authors) {
        authorRepository.save(author);
      }
      log.info("author table seeded!");
    } else {
      log.info("author seeding not required!");
    }
  }

  private void seedBookTable() {
    List<Book> books = bookRepository.findAll();
    if (books.size() <= 0) {
      Author a1 = authorRepository.findByNameIgnoreCase("Ria Sukma Wijaya");

      if (Objects.isNull(a1)) {
        a1 = new Author("Ria Sukma Wijaya");
        authorRepository.save(a1);
      }

      Book b11 = new Book("Off The Record", a1, 2018, 10);
      Book b12 = new Book("Off The Record 2", a1, 2019, 10);

      books = new ArrayList<>(Arrays.asList(b11, b12));
      for (Book book : books) {
        bookRepository.save(book);
      }
      log.info("book table seeded!");
    } else {
      log.info("book seeding not required!");
    }
  }
}
