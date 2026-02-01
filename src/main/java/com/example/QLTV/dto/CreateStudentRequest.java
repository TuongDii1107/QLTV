package com.example.QLTV.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateStudentRequest {
    // Student info
    private String studentCode;
    private String faculty;
    private String clazz;

    // User info
    private String username;
    private String email;
}
