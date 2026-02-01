package com.example.QLTV.service;

import com.example.QLTV.dto.CreateStudentRequest;
import com.example.QLTV.entity.Student;
import com.example.QLTV.entity.User;
import com.example.QLTV.entity.e_num.StudentStatus;
import com.example.QLTV.entity.e_num.UserStatus;
import com.example.QLTV.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    // 1. Lấy danh sách sinh viên
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 2. Tạo sinh viên
    public Student createStudent(CreateStudentRequest request) {

        //Tạo user trước
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setStatus(UserStatus.INACTIVE); // chưa kích hoạt
        user.setPassword("temp-password");   // sau gửi mail reset

        userRepository.save(user);

        //Tạo student
        Student student = new Student();
        student.setStudentCode(request.getStudentCode());
        student.setFaculty(request.getFaculty());
        student.setClazz(request.getClazz());
        student.setStudentStatus(StudentStatus.ACTIVE);
        student.setFineBalance(0.0);
        student.setDebt(0.0);
        student.setUser(user);

        return studentRepository.save(student);
    }

    // 3. Xem chi tiết
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // 4. Khóa / Mở khóa
    public Student updateStatus(Long id, StudentStatus status) {
        Student student = getStudentById(id);
        student.setStatus(status);
        return studentRepository.save(student);
    }
}
