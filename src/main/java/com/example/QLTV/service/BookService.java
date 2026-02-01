package com.example.QLTV.service;

import com.example.QLTV.entity.Book;
import com.example.QLTV.entity.BookCopy;
import com.example.QLTV.entity.e_num.BookCondition;
import com.example.QLTV.entity.e_num.BookStatus;
import com.example.QLTV.exception.ApiException;
import com.example.QLTV.exception.ErrorCode;
import com.example.QLTV.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    // GET /api/books (filter)
    public List<Book> getBooks(String title,
                               String author,
                               String category,
                               String isbn,
                               String keyword) {

        if (StringUtils.hasText(isbn)) {
            return bookRepo.findByIsbn(isbn).map(List::of).orElse(List.of());
        }
        if (StringUtils.hasText(title)) {
            return bookRepo.findByTitleContainingIgnoreCase(title);
        }
        if (StringUtils.hasText(author)) {
            return bookRepo.findByAuthorContainingIgnoreCase(author);
        }
        if (StringUtils.hasText(category)) {
            return bookRepo.findByCategoryContainingIgnoreCase(category);
        }
        if (StringUtils.hasText(keyword)) {
            return bookRepo
                    .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                            keyword, keyword, keyword);
        }
        return bookRepo.findAll();
    }

    // POST /api/books
    public Book create(Book book, int quantity) {

        for (int i = 1; i <= quantity; i++) {
            BookCopy copy = new BookCopy();
            copy.setBarcode(book.getIsbn() + "-" + i);
            copy.setConditionStatus(BookCondition.NORMAL);
            copy.setCirculationStatus(BookStatus.AVAILABLE);
            book.addCopy(copy);
        }
        return bookRepo.save(book);
    }

    // GET /api/books/{id}
    public Book getDetail(UUID id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.BOOK_NOT_FOUND));
    }

    // PUT /api/books/{id}
    public Book update(UUID id, Book req) {
        Book book = getDetail(id);
        book.setTitle(req.getTitle());
        book.setAuthor(req.getAuthor());
        book.setCategory(req.getCategory());
        book.setDescription(req.getDescription());
        book.setPublisher(req.getPublisher());
        book.setPublishedYear(req.getPublishedYear());
        book.setPrice(req.getPrice());
        book.setShelfCode(req.getShelfCode());
        return book;
    }

    // DELETE /api/books/{id} (soft delete)
    public void delete(UUID id) {
        Book book = getDetail(id);
        book.setIsDeleted(true);
    }
    // PUT /api/books/{id}/shelf
    public Book updateShelf(UUID id, String shelfCode) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.BOOK_NOT_FOUND));

        book.setShelfCode(shelfCode);
        return bookRepo.save(book);
    }
    //Path
    public Book updateStock(UUID bookId, int delta) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new ApiException(ErrorCode.BOOK_NOT_FOUND));

        // TĂNG SỐ LƯỢNG
        if (delta > 0) {
            int start = book.getCopies().size() + 1;
            for (int i = 0; i < delta; i++) {
                BookCopy copy = new BookCopy();
                copy.setBarcode(book.getIsbn() + "-" + (start + i));
                copy.setConditionStatus(BookCondition.NORMAL);
                copy.setCirculationStatus(BookStatus.AVAILABLE);
                book.addCopy(copy);
            }
        }

        // GIẢM SỐ LƯỢNG
        if (delta < 0) {
            int removeCount = Math.abs(delta);

            List<BookCopy> removable = book.getCopies().stream()
                    .filter(c -> c.getCirculationStatus() == BookStatus.AVAILABLE)
                    .limit(removeCount)
                    .toList();

            if (removable.size() < removeCount) {
                throw new ApiException(ErrorCode.NOT_ENOUGH_AVAILABLE_COPIES);
            }

            removable.forEach(book.getCopies()::remove);
        }

        return bookRepo.save(book);
    }

}
