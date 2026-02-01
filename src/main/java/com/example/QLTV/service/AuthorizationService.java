package com.example.QLTV.service;

import com.example.QLTV.entity.Role;
import com.example.QLTV.entity.UserRole;
import com.example.QLTV.entity.e_num.RoleName;
import com.example.QLTV.exception.ApiException;
import com.example.QLTV.exception.ErrorCode;
import com.example.QLTV.repository.RoleRepository;
import com.example.QLTV.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorizationService {
    private final UserRoleRepository userRoleRepo;
    private final RoleRepository roleRepo;

    public AuthorizationService(UserRoleRepository userRoleRepo,
                                RoleRepository roleRepo) {
        this.userRoleRepo = userRoleRepo;
        this.roleRepo = roleRepo;
    }

    public RoleName getRoleByUserId(UUID userId) {

        UserRole userRole = userRoleRepo.findByUserId(userId)
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new ApiException(ErrorCode.USER_NO_ROLE));

        Role role = roleRepo.findById(userRole.getRole().getId())
                .orElseThrow(() ->
                        new ApiException(ErrorCode.ROLE_NOT_FOUND));

        return role.getName();
    }
}
