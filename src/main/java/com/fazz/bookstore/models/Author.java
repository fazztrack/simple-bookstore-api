package com.fazz.bookstore.models;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
public class Author {
  @Id
  @UuidGenerator
  private String id;
  private String name;

  @JsonIgnore
  private Boolean status = true;

  @CreationTimestamp
  @JsonIgnore
  private Instant createdAt;

  @UpdateTimestamp
  @JsonIgnore
  private Instant updatedAt;

  public Author(String name) {
    this.name = name;
  }
}
