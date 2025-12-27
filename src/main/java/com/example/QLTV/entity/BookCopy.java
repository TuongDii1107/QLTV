package com.example.QLTV.entity;
import com.example.QLTV.entity.e_num.BookCondition;
import com.example.QLTV.entity.e_num.BookStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "book_copy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookCopy extends BaseEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "CHAR(36)")
    UUID id;

    @Column(unique = true)
    String barcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "circulation_status")
    BookStatus circulationStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_status")
    BookCondition conditionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    Book book;

    @OneToMany(mappedBy = "bookCopy")
    List<Loan> loans;

    @OneToMany(mappedBy = "bookCopy")
    List<Reservation> reservations;
}
