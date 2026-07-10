package com.bookstore.service;

import com.bookstore.dto.request.PlaceOrderRequest;
import com.bookstore.dto.response.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(PlaceOrderRequest request);

}
