package com.example.QLTV.entity;
import com.example.QLTV.entity.e_num.BookCondition;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "return_transaction")
public class ReturnTransaction extends BaseEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    UUID id;

    @Column(name = "return_at")
    LocalDateTime returnAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_condition")
    BookCondition bookCondition;

    String note;

    @OneToOne
    @JoinColumn(name = "loan_id")
    Loan loan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    Staff staff;
}
