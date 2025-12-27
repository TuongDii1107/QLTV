package com.example.QLTV.entity;
import com.example.QLTV.entity.e_num.UserStatus;
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
public class Student extends BaseEntity {
    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "CHAR(36)")
    UUID id;

    @Column(unique = true, nullable = false)
    String studentCode;

    String faculty;
    String clazz;

    @Enumerated(EnumType.STRING)
    UserStatus status;

    @Column(name = "fine_balance")
    Double fineBalance = 0.0;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "student")
    List<Loan> loans;

    @OneToMany(mappedBy = "student")
    List<Reservation> reservations;

    @OneToMany(mappedBy = "student")
    List<Review> reviews;
}
