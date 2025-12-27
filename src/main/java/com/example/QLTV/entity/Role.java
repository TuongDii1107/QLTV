package com.example.QLTV.entity;

import com.example.QLTV.entity.e_num.RoleName;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "role")
public class Role {
    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "CHAR(36)")
    UUID id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    RoleName name;

    @OneToMany(mappedBy = "role")
    List<UserRole> userRoles;
}
