package com.bookstore.service;

import com.bookstore.dto.request.PlaceOrderRequest;
import com.bookstore.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(PlaceOrderRequest request);
    List<OrderResponse> getMyOrders();
    OrderResponse getOrderById(Long id);
    List<OrderResponse> getAllOrders();

}
