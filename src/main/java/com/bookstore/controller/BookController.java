package com.bookstore.controller;

import com.bookstore.dto.request.BookRequest;
import com.bookstore.dto.response.BookResponse;
import com.bookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import com.bookstore.enums.Genre;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public BookResponse addBook(@Valid @RequestBody BookRequest request) {
        return bookService.addBook(request);
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }


    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public BookResponse updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookRequest request) {

        return bookService.updateBook(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/search/title")
    public List<BookResponse> searchByTitle(
            @RequestParam String keyword) {

        return bookService.searchByTitle(keyword);
    }

    @GetMapping("/search/author")
    public List<BookResponse> searchByAuthor(
            @RequestParam String keyword) {

        return bookService.searchByAuthor(keyword);
    }

    @GetMapping("/search/genre")
    public List<BookResponse> searchByGenre(
            @RequestParam Genre genre) {

        return bookService.searchByGenre(genre);
    }

    @GetMapping("/page")
    public Page<BookResponse> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return bookService.getBooks(page, size, sortBy, direction);
    }
}