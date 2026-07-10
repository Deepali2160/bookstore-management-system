package com.bookstore.dto.response;

import com.bookstore.enums.OrderStatus;
import com.bookstore.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderResponse {

    private Long id;

    private Long userId;

    private BigDecimal totalAmount;

    private OrderStatus orderStatus;

    private PaymentStatus paymentStatus;
}