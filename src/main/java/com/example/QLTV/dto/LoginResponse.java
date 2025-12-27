package com.example.QLTV.dto;

import com.example.QLTV.entity.e_num.RoleName;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    UUID userId;
    String username;
    RoleName role;
}
