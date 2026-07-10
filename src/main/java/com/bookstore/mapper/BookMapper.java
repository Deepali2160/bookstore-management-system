package com.bookstore.mapper;

import com.bookstore.dto.request.BookRequest;
import com.bookstore.dto.response.BookResponse;
import com.bookstore.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(BookRequest request) {

        Book book = new Book();

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setStockQuantity(request.getStockQuantity());
        book.setGenre(request.getGenre());
        book.setDescription(request.getDescription());
        book.setImageUrl(request.getImageUrl());

        return book;
    }

    public BookResponse toResponse(Book book) {

        BookResponse response = new BookResponse();

        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setIsbn(book.getIsbn());
        response.setPrice(book.getPrice());
        response.setStockQuantity(book.getStockQuantity());
        response.setGenre(book.getGenre());
        response.setDescription(book.getDescription());
        response.setImageUrl(book.getImageUrl());

        return response;
    }
}