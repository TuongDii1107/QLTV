package com.example.QLTV.controller;

import com.example.QLTV.entity.e_num.RoleName;
import com.example.QLTV.exception.ApiException;
import com.example.QLTV.exception.ErrorCode;
import com.example.QLTV.service.AuthorizationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AuthorizationService authzService;

    public AdminController(AuthorizationService authzService) {
        this.authzService = authzService;
    }

    @GetMapping("/users")
    public String getAllUsers(@RequestParam UUID userId) {

        RoleName role = authzService.getRoleByUserId(userId);

        if (role != RoleName.ADMIN) {
            throw new ApiException(ErrorCode.FORBIDDEN);
        }

        return "ADMIN được phép truy cập";
    }
}
