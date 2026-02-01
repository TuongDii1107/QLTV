package com.example.QLTV.service;

import com.example.QLTV.entity.BookCopy;
import com.example.QLTV.entity.e_num.BookCondition;
import com.example.QLTV.entity.e_num.BookStatus;
import com.example.QLTV.exception.ApiException;
import com.example.QLTV.exception.ErrorCode;
import com.example.QLTV.repository.BookCopyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BookCopyService {

    private final BookCopyRepository repo;

    public BookCopyService(BookCopyRepository repo) {
        this.repo = repo;
    }

    // 1️⃣ Lấy danh sách bản in (có filter status)
    public List<BookCopy> getByBook(UUID bookId, BookStatus status) {
        if (status == null) {
            return repo.findByBookId(bookId);
        }
        return repo.findByBookIdAndCirculationStatus(bookId, status);
    }

    // 2️⃣ Xem chi tiết bản in
    public BookCopy getDetail(UUID copyId) {
        return repo.findById(copyId)
                .orElseThrow(() -> new ApiException(ErrorCode.BOOK_COPY_NOT_FOUND));
    }

    // 3️⃣ Cập nhật trạng thái lưu thông
    public BookCopy updateStatus(UUID copyId, BookCondition condition) {
        BookCopy copy = getDetail(copyId);

        copy.setConditionStatus(condition);

        if (condition == BookCondition.NORMAL
                || condition == BookCondition.DAMAGED_LIGHT) {
            copy.setCirculationStatus(BookStatus.AVAILABLE);
        } else if (condition == BookCondition.LOST) {
            copy.setCirculationStatus(BookStatus.LOST);
        } else {
            copy.setCirculationStatus(BookStatus.DAMAGED);
        }

        return repo.save(copy);
    }

    // 4️⃣ Xóa bản in
    public void delete(UUID copyId) {
        BookCopy copy = getDetail(copyId);
        repo.delete(copy);
    }
}
