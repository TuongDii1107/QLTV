package com.example.QLTV.repository;

import com.example.QLTV.entity.BookCopy;
import com.example.QLTV.entity.e_num.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, UUID> {
    List<BookCopy> findByBookId(UUID bookId);

    List<BookCopy> findByBookIdAndCirculationStatus(UUID bookId, BookStatus status);
}
