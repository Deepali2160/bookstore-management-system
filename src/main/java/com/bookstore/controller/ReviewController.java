package com.bookstore.controller;

import com.bookstore.dto.request.ReviewRequest;
import com.bookstore.dto.response.ReviewResponse;
import com.bookstore.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/api/reviews")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ReviewResponse addReview(
            @Valid @RequestBody ReviewRequest request) {

        return reviewService.addReview(request);
    }

    @GetMapping("/api/books/{bookId}/reviews")
    public List<ReviewResponse> getReviewsByBook(
            @PathVariable Long bookId) {

        return reviewService.getReviewsByBook(bookId);
    }

    @DeleteMapping("/api/reviews/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteReview(@PathVariable Long id) {

        reviewService.deleteReview(id);
    }
}