package com.example.QLTV.service;

import com.example.QLTV.entity.Role;
import com.example.QLTV.exception.ApiException;
import com.example.QLTV.exception.ErrorCode;
import com.example.QLTV.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {

    private final RoleRepository roleRepo;

    public RoleService(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<Role> getAll() {
        return roleRepo.findAll();
    }

    public Role create(Role role) {
        return roleRepo.save(role);
    }

    public Role update(UUID id, Role role) {
        Role existing = roleRepo.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.ROLE_NOT_FOUND));

        existing.setName(role.getName());
        existing.setDescription(role.getDescription());
        return roleRepo.save(existing);
    }

    public void delete(UUID id) {
        roleRepo.deleteById(id);
    }
}

