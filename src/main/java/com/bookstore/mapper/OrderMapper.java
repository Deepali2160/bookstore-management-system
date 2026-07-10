package com.bookstore.mapper;

import com.bookstore.dto.response.OrderResponse;
import com.bookstore.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toResponse(Order order) {

        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setUserId(order.getUser().getId());
        response.setTotalAmount(order.getTotalAmount());
        response.setOrderStatus(order.getOrderStatus());
        response.setPaymentStatus(order.getPaymentStatus());

        return response;
    }
}