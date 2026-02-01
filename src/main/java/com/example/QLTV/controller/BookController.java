package com.example.QLTV.controller;

import com.example.QLTV.entity.Book;
import com.example.QLTV.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String keyword
    ) {
        return service.getBooks(title, author, category, isbn, keyword);
    }

    @PostMapping
    public Book create(@RequestBody Book book,
                       @RequestParam int quantity) {
        return service.create(book, quantity);
    }

    @GetMapping("/{id}")
    public Book detail(@PathVariable UUID id) {
        return service.getDetail(id);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable UUID id,
                       @RequestBody Book book) {
        return service.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
    @PutMapping("/{id}/shelf")
    public Book updateShelf(@PathVariable UUID id,
                            @RequestParam String shelfCode) {
        return service.updateShelf(id, shelfCode);
    }
    @PatchMapping("/{id}/stock")
    public Book updateStock(@PathVariable UUID id,
                            @RequestParam int delta) {
        return service.updateStock(id, delta);
    }
}
