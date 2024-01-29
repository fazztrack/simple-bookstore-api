package com.fazz.bookstore.services.author;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fazz.bookstore.models.Author;
import com.fazz.bookstore.payloads.AuthorRequest;
import com.fazz.bookstore.payloads.Response;
import com.fazz.bookstore.payloads.ResponseData;
import com.fazz.bookstore.payloads.ResponseMeta;

public interface AuthorInterface {
  ResponseData<Author> postAuthor(AuthorRequest request);

  ResponseMeta<List<Author>> getAuthors(Boolean cms, Pageable pageable);

  ResponseData<Author> putAuthor(String id, AuthorRequest request);

  Response deleteAuthor(String id);

  ResponseData<Author> getAuthorById(String id);

}
