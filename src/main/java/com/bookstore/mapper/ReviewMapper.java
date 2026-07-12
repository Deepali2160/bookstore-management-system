package com.bookstore.mapper;

import com.bookstore.dto.response.ReviewResponse;
import com.bookstore.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewResponse toResponse(Review review) {

        ReviewResponse response = new ReviewResponse();

        response.setId(review.getId());

        response.setUserId(review.getUser().getId());
        response.setUserName(review.getUser().getName());

        response.setBookId(review.getBook().getId());
        response.setBookTitle(review.getBook().getTitle());

        response.setRating(review.getRating());
        response.setComment(review.getComment());
        response.setCreatedAt(review.getCreatedAt());

        return response;
    }
}