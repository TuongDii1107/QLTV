package com.example.QLTV.exception;

import lombok.*;

@Getter
public enum ErrorCode {
    USERNAME_NOT_FOUND("USERNAME_NOT_FOUND", "Username không tồn tại"),
    WRONG_PASSWORD("WRONG_PASSWORD", "Mật khẩu không đúng"),
    USER_NO_ROLE("USER_NO_ROLE", "Người dùng chưa được gán vai trò"),
    ROLE_NOT_FOUND("ROLE_NOT_FOUND", "Vai trò không tồn tại"),
    ACCOUNT_LOCKED("ACCOUNT_LOCKED", "Tài khoản đã bị khóa"),
    FORBIDDEN("FORBIDDEN", "Bạn không có quyền truy cập");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
