package com.fazz.bookstore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fazz.bookstore.models.Book;

public interface BookRepository extends JpaRepository<Book, String> {
  List<Book> findByStatusTrue();

  Page<Book> findByStatusTrue(Pageable pageable);
}
