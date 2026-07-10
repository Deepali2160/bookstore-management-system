package com.bookstore.controller;

import com.bookstore.dto.request.PlaceOrderRequest;
import com.bookstore.dto.response.OrderResponse;
import com.bookstore.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public OrderResponse placeOrder(
            @Valid @RequestBody PlaceOrderRequest request) {

        return orderService.placeOrder(request);
    }
}