package com.bookstore.service;

import com.bookstore.dto.request.BookRequest;
import com.bookstore.dto.response.BookResponse;
import com.bookstore.enums.Genre;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    BookResponse addBook(BookRequest request);

    List<BookResponse> getAllBooks();

    BookResponse getBookById(Long id);

    BookResponse updateBook(Long id, BookRequest request);

    void deleteBook(Long id);

    List<BookResponse> searchByTitle(String title);

    List<BookResponse> searchByAuthor(String author);

    List<BookResponse> searchByGenre(Genre genre);

    Page<BookResponse> getBooks(int page,
                                int size,
                                String sortBy,
                                String direction);
}