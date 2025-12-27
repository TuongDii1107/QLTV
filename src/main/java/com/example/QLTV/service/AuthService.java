package com.example.QLTV.service;

import com.example.QLTV.dto.LoginRequest;
import com.example.QLTV.dto.LoginResponse;
import com.example.QLTV.entity.Role;
import com.example.QLTV.entity.User;
import com.example.QLTV.entity.UserRole;
import com.example.QLTV.exception.ApiException;
import com.example.QLTV.exception.ErrorCode;
import com.example.QLTV.repository.RoleRepository;
import com.example.QLTV.repository.UserRepository;
import com.example.QLTV.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final UserRoleRepository userRoleRepo;
    private final RoleRepository roleRepo;

    public AuthService(UserRepository userRepo,
                       UserRoleRepository userRoleRepo,
                       RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.roleRepo = roleRepo;
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Username không tồn tại"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Sai mật khẩu");
        }

        UserRole userRole = userRoleRepo.findByUserId(user.getId())
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User chưa được gán role"));

        Role role = roleRepo.findById(userRole.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role không tồn tại"));

        return new LoginResponse(
                user.getId(),
                user.getUsername(),
                role.getName()
        );
    }
}
