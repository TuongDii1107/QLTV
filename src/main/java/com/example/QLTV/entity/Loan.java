package com.example.QLTV.entity;
import com.example.QLTV.entity.e_num.LoanStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Loan extends BaseEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "CHAR(36)")
    UUID id;

    @Column(name = "borrowed_at")
    LocalDateTime borrowedAt;

    @Column(name = "due_date")
    LocalDateTime dueDate;

    @Column(name = "returned_at")
    LocalDateTime returnedAt;

    @Enumerated(EnumType.STRING)
    LoanStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_copy_id", nullable = false)
    BookCopy bookCopy;

    @OneToOne(mappedBy = "loan", cascade = CascadeType.ALL)
    ReturnTransaction returnTransaction;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    List<Fine> fines;
}
