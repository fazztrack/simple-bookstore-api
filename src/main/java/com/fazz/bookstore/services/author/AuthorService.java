package com.fazz.bookstore.services.author;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fazz.bookstore.dao.AuthorRepository;
import com.fazz.bookstore.models.Author;
import com.fazz.bookstore.payloads.AuthorRequest;
import com.fazz.bookstore.payloads.Meta;
import com.fazz.bookstore.payloads.Response;
import com.fazz.bookstore.payloads.ResponseData;
import com.fazz.bookstore.payloads.ResponseMeta;

@Service
public class AuthorService implements AuthorInterface {
  @Autowired
  AuthorRepository authorRepository;

  @Override
  public ResponseData<Author> postAuthor(AuthorRequest request) {
    try {
      Author author = new Author(request.getAuthorName());

      authorRepository.save(author);

      return new ResponseData<Author>(HttpStatus.CREATED.toString(), true, author);
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public ResponseMeta<List<Author>> getAuthors(Boolean cms, Pageable pageable) {
    try {
      Page<Author> authorPage = cms ? authorRepository.findAll(pageable) : authorRepository.findByStatusTrue(pageable);

      List<Author> authors = authorPage.getContent();

      Meta meta = new Meta(authorPage.getNumber() + 1, (authorPage.getNumber() < 1) ? null : authorPage.getNumber(),
          ((authorPage.getNumber() + 2) <= authorPage.getTotalPages()) ? (authorPage.getNumber() + 2) : null,
          authorPage.getTotalElements());

      return new ResponseMeta<List<Author>>(HttpStatus.OK.toString(), true, authors, meta);
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public ResponseData<Author> putAuthor(String id, AuthorRequest request) {
    try {
      // find author
      Author author = authorRepository.findById(id)
          .orElseThrow(() -> new NoSuchElementException("id " + id + " is not found!"));

      author.setName(request.getAuthorName());

      authorRepository.save(author);

      return new ResponseData<Author>(HttpStatus.OK.toString(), true, author);
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public Response deleteAuthor(String id) {
    try {
      // find author
      Author author = authorRepository.findById(id)
          .orElseThrow(() -> new NoSuchElementException("id " + id + " is not found!"));

      author.setStatus(false);
      authorRepository.save(author);

      return new Response(HttpStatus.OK.toString(), true);
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public ResponseData<Author> getAuthorById(String id) {
    try {
      // find author
      Author author = authorRepository.findById(id)
          .orElseThrow(() -> new NoSuchElementException("id " + id + " is not found!"));

      return new ResponseData<Author>(HttpStatus.OK.toString(), true, author);
    } catch (Exception e) {
      throw e;
    }
  }

}
