package com.example.QLTV.controller;

import com.example.QLTV.entity.User;
import com.example.QLTV.service.StaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // Lấy danh sách nhân sự
    @GetMapping
    public List<User> getAllStaff() {
        return staffService.getAllStaff();
    }

    // Tạo nhân sự
    @PostMapping
    public User createStaff(@RequestBody User user) {
        return staffService.createStaff(user);
    }

    // Cập nhật nhân sự
    @PutMapping("/{id}")
    public User updateStaff(@PathVariable UUID id,
                            @RequestBody User user) {
        return staffService.updateStaff(id, user);
    }

    // Xóa nhân sự
    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable UUID id) {
        staffService.deleteStaff(id);
    }
}
