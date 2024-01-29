package com.fazz.bookstore.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fazz.bookstore.models.Author;

public interface AuthorRepository extends JpaRepository<Author, String> {
  Author findByNameIgnoreCase(String name);

  Page<Author> findByStatusTrue(Pageable pageable);
}
