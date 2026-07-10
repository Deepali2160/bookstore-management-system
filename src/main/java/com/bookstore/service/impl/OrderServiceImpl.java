package com.bookstore.service.impl;

import com.bookstore.dto.request.PlaceOrderRequest;
import com.bookstore.dto.response.OrderResponse;
import com.bookstore.entity.Book;
import com.bookstore.entity.Order;
import com.bookstore.entity.OrderItem;
import com.bookstore.entity.User;
import com.bookstore.enums.OrderStatus;
import com.bookstore.enums.PaymentStatus;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.mapper.OrderMapper;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.OrderItemRepository;
import com.bookstore.repository.OrderRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(UserRepository userRepository,
                            BookRepository bookRepository,
                            OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            OrderMapper orderMapper) {

        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(PlaceOrderRequest request) {

        // Get logged-in user
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        // Find book
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));

        // Check stock
        if (book.getStockQuantity() < request.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock available");
        }

        // Calculate total
        BigDecimal totalAmount = book.getPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        // Create Order
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalAmount);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setPaymentStatus(PaymentStatus.PENDING);

        orderRepository.save(order);

        // Create Order Item
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setBook(book);
        item.setQuantity(request.getQuantity());
        item.setUnitPrice(book.getPrice());
        item.setSubtotal(totalAmount);

        orderItemRepository.save(item);

        // Reduce stock
        book.setStockQuantity(
                book.getStockQuantity() - request.getQuantity());

        bookRepository.save(book);

        return orderMapper.toResponse(order);
    }
}