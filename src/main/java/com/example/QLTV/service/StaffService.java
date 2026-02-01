package com.example.QLTV.service;

import com.example.QLTV.entity.User;
import com.example.QLTV.exception.ApiException;
import com.example.QLTV.exception.ErrorCode;
import com.example.QLTV.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StaffService {
    private final UserRepository userRepo;

    public StaffService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllStaff() {
        return userRepo.findAll();
    }

    public User createStaff(User user) {
        user.setId(UUID.randomUUID());
        return userRepo.save(user);
    }

    public User updateStaff(UUID id, User user) {
        User existing = userRepo.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.USERNAME_NOT_FOUND));

        existing.setUsername(user.getUsername());
        existing.setStatus(user.getStatus());
        return userRepo.save(existing);
    }

    public void deleteStaff(UUID id) {
        userRepo.deleteById(id);
    }
}
