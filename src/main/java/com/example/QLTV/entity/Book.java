package com.example.QLTV.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book extends BaseEntity {

    @Id
    @UuidGenerator
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    UUID id;

    String title;
    String author;

    @Column(unique = true, nullable = false)
    String isbn;

    String category;
    String description;
    String publisher;

    String publishedYear;
    Double price;

    @Column(name = "shelf_code")
    String shelfCode;

    @OneToMany(
            mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<BookCopy> copies = new ArrayList<>();

    // helper
    public void addCopy(BookCopy copy) {
        copies.add(copy);
        copy.setBook(this);
    }

}