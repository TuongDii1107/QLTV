package com.example.QLTV.controller;

import com.example.QLTV.entity.BookCopy;
import com.example.QLTV.entity.e_num.BookCondition;
import com.example.QLTV.entity.e_num.BookStatus;
import com.example.QLTV.service.BookCopyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book-copies")
public class BookCopyController {

    private final BookCopyService service;

    public BookCopyController(BookCopyService service) {
        this.service = service;
    }

    // 1️⃣ Lấy danh sách bản in
    @GetMapping
    public List<BookCopy> getByBook(
            @RequestParam UUID bookId,
            @RequestParam(required = false) BookStatus status
    ) {
        return service.getByBook(bookId, status);
    }

    // 2️⃣ Xem chi tiết bản in
    @GetMapping("/{copyId}")
    public BookCopy detail(@PathVariable UUID copyId) {
        return service.getDetail(copyId);
    }

    // 3️⃣ Cập nhật trạng thái lưu thông
    @PatchMapping("/{copyId}/circulation-status")
    public BookCopy updateStatus(
            @PathVariable UUID copyId,
            @RequestParam BookCondition condition
    ) {
        return service.updateStatus(copyId, condition);
    }

    // 4️⃣ Xóa bản in
    @DeleteMapping("/{copyId}")
    public void delete(@PathVariable UUID copyId) {
        service.delete(copyId);
    }
}
