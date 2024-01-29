package com.fazz.bookstore.models;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
  @Id
  @UuidGenerator
  private String id;
  private String title;

  @ManyToOne
  @JoinColumn(name = "id_author")
  private Author author;

  @Column(length = 4)
  private Integer year;
  private Integer stock = 0;

  @JsonIgnore
  private Boolean status = true;

  @CreationTimestamp
  @JsonIgnore
  private Instant createdAt;

  @UpdateTimestamp
  @JsonIgnore
  private Instant updatedAt;

  public Book(String title, Author author, Integer year, Integer stock) {
    this.title = title;
    this.author = author;
    this.year = year;
    this.stock = stock;
  }
}
