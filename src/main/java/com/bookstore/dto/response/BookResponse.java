package com.bookstore.dto.response;

import com.bookstore.enums.Genre;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BookResponse {

    private Long id;

    private String title;

    private String author;

    private String isbn;

    private BigDecimal price;

    private Integer stockQuantity;

    private Genre genre;

    private String description;

    private String imageUrl;
}