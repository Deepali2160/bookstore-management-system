package com.bookstore.service.impl;

import com.bookstore.dto.request.ReviewRequest;
import com.bookstore.dto.response.ReviewResponse;
import com.bookstore.entity.Book;
import com.bookstore.entity.Review;
import com.bookstore.entity.User;
import com.bookstore.exception.DuplicateResourceException;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.mapper.ReviewMapper;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.ReviewRepository;
import com.bookstore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private User user;
    private Book book;
    private Review review;
    private ReviewRequest request;
    private ReviewResponse response;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setId(1L);
        user.setName("Deepali");
        user.setEmail("deepali@gmail.com");

        book = new Book();
        book.setId(2L);
        book.setTitle("Spring Boot");

        review = new Review();
        review.setUser(user);
        review.setBook(book);
        review.setRating(5);
        review.setComment("Excellent Book");

        request = new ReviewRequest();
        request.setBookId(2L);
        request.setRating(5);
        request.setComment("Excellent Book");

        response = new ReviewResponse();
        response.setBookId(2L);
        response.setRating(5);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "deepali@gmail.com",
                        null
                )
        );
    }

    @Test
    void addReview_Success() {

        when(userRepository.findByEmail("deepali@gmail.com"))
                .thenReturn(Optional.of(user));

        when(bookRepository.findById(2L))
                .thenReturn(Optional.of(book));

        when(reviewRepository.findByUserAndBook(user, book))
                .thenReturn(Optional.empty());

        when(reviewRepository.save(any(Review.class)))
                .thenReturn(review);

        when(reviewMapper.toResponse(any(Review.class)))
                .thenReturn(response);

        ReviewResponse result = reviewService.addReview(request);

        assertNotNull(result);
        assertEquals(2L, result.getBookId());
        assertEquals(5, result.getRating());

        verify(userRepository).findByEmail("deepali@gmail.com");
        verify(bookRepository).findById(2L);
        verify(reviewRepository).findByUserAndBook(user, book);
        verify(reviewRepository).save(any(Review.class));
        verify(reviewMapper).toResponse(any(Review.class));
    }

    @Test
    void addReview_DuplicateReview() {

        when(userRepository.findByEmail("deepali@gmail.com"))
                .thenReturn(Optional.of(user));

        when(bookRepository.findById(2L))
                .thenReturn(Optional.of(book));

        when(reviewRepository.findByUserAndBook(user, book))
                .thenReturn(Optional.of(review));

        assertThrows(
                DuplicateResourceException.class,
                () -> reviewService.addReview(request)
        );

        verify(reviewRepository, never()).save(any(Review.class));
    }

    @Test
    void addReview_BookNotFound() {

        when(userRepository.findByEmail("deepali@gmail.com"))
                .thenReturn(Optional.of(user));

        when(bookRepository.findById(2L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> reviewService.addReview(request)
        );

        verify(reviewRepository, never()).save(any());
    }

    @Test
    void getReviewsByBook() {

        when(bookRepository.findById(2L))
                .thenReturn(Optional.of(book));

        when(reviewRepository.findByBook(book))
                .thenReturn(List.of(review));

        when(reviewMapper.toResponse(review))
                .thenReturn(response);

        var result = reviewService.getReviewsByBook(2L);

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(bookRepository).findById(2L);
        verify(reviewRepository).findByBook(book);
        verify(reviewMapper).toResponse(review);
    }

    @Test
    void deleteReview() {

        when(reviewRepository.findById(1L))
                .thenReturn(Optional.of(review));

        reviewService.deleteReview(1L);

        verify(reviewRepository).findById(1L);
        verify(reviewRepository).delete(review);
    }

    @Test
    void deleteReview_ReviewNotFound() {

        when(reviewRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> reviewService.deleteReview(1L)
        );

        verify(reviewRepository, never()).delete(any());
    }
}