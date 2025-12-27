package com.example.QLTV.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor

public class Book extends BaseEntity {
    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "CHAR(36)")
    UUID id;

    String title;
    String author;

    @Column(unique = true)
    String isbn;

    String category;
    String publisher;

    @Column(name = "published_year")
    String publishedYear;

    Double price;
    String shelfCode;

    @OneToMany(mappedBy = "book")
    List<BookCopy> copies;

    @OneToMany(mappedBy = "book")
    List<Review> reviews;
}