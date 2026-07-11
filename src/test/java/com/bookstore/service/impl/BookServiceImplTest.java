package com.bookstore.service.impl;

import com.bookstore.dto.request.BookRequest;
import com.bookstore.dto.response.BookResponse;
import com.bookstore.entity.Book;
import com.bookstore.enums.Genre;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.mapper.BookMapper;
import com.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;
    private BookRequest request;
    private BookResponse response;

    @BeforeEach
    void setUp() {

        request = new BookRequest();
        request.setTitle("Clean Code");
        request.setAuthor("Robert C. Martin");
        request.setIsbn("9780132350884");
        request.setPrice(new BigDecimal("799"));
        request.setStockQuantity(20);
        request.setGenre(Genre.TECHNOLOGY);

        book = new Book();
        book.setId(1L);
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setStockQuantity(request.getStockQuantity());
        book.setGenre(request.getGenre());

        response = new BookResponse();
        response.setId(1L);
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setIsbn(book.getIsbn());
        response.setPrice(book.getPrice());
        response.setStockQuantity(book.getStockQuantity());
        response.setGenre(book.getGenre());
    }
    @Test
    void shouldAddBookSuccessfully() {

        // Arrange
        when(bookRepository.existsByIsbn(request.getIsbn()))
                .thenReturn(false);

        when(bookMapper.toEntity(request))
                .thenReturn(book);

        when(bookRepository.save(book))
                .thenReturn(book);

        when(bookMapper.toResponse(book))
                .thenReturn(response);

        // Act
        BookResponse result = bookService.addBook(request);

        // Assert
        assertNotNull(result);
        assertEquals("Clean Code", result.getTitle());
        assertEquals("Robert C. Martin", result.getAuthor());

        verify(bookRepository).existsByIsbn(request.getIsbn());
        verify(bookRepository).save(book);
    }

    @Test
    void shouldReturnBookById() {

        // Arrange
        when(bookRepository.findById(1L))
                .thenReturn(java.util.Optional.of(book));

        when(bookMapper.toResponse(book))
                .thenReturn(response);

        // Act
        BookResponse result = bookService.getBookById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Clean Code", result.getTitle());

        verify(bookRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenBookNotFound() {

        // Arrange
        when(bookRepository.findById(1L))
                .thenReturn(java.util.Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> bookService.getBookById(1L)
        );

        assertEquals("Book not found with id: 1", exception.getMessage());

        verify(bookRepository).findById(1L);
    }

    @Test
    void shouldUpdateBookSuccessfully() {

        // Arrange
        request.setTitle("Clean Code 2nd Edition");
        request.setPrice(new BigDecimal("999"));

        when(bookRepository.findById(1L))
                .thenReturn(java.util.Optional.of(book));

        when(bookRepository.save(book))
                .thenReturn(book);

        when(bookMapper.toResponse(book))
                .thenReturn(response);

        // Act
        BookResponse result = bookService.updateBook(1L, request);

        // Assert
        assertNotNull(result);

        assertEquals("Clean Code 2nd Edition", book.getTitle());
        assertEquals(new BigDecimal("999"), book.getPrice());

        verify(bookRepository).findById(1L);
        verify(bookRepository).save(book);
    }

    @Test
    void shouldDeleteBookSuccessfully() {

        // Arrange
        when(bookRepository.findById(1L))
                .thenReturn(java.util.Optional.of(book));

        // Act
        bookService.deleteBook(1L);

        // Assert
        verify(bookRepository).findById(1L);
        verify(bookRepository).delete(book);
    }
}