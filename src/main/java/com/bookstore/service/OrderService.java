package com.bookstore.service;

import com.bookstore.dto.request.PlaceOrderRequest;
import com.bookstore.dto.request.UpdateOrderStatusRequest;
import com.bookstore.dto.response.OrderResponse;

import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(PlaceOrderRequest request);
    List<OrderResponse> getMyOrders();
    OrderResponse getOrderById(Long id);
    Page<OrderResponse> getAllOrders(
            int page,
            int size,
            String sortBy,
            String direction);

    OrderResponse updateOrderStatus(Long id, UpdateOrderStatusRequest request);

}
