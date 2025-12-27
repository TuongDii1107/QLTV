package com.example.QLTV.entity;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleID implements Serializable {
    @Column(name = "user_id", columnDefinition = "CHAR(36)")
    UUID userId;
    @Column(name = "role_id", columnDefinition = "CHAR(36)")
    UUID roleId;
}
