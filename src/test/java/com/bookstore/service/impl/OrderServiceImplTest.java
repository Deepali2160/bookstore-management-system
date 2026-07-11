package com.bookstore.service.impl;

import com.bookstore.dto.request.PlaceOrderRequest;
import com.bookstore.dto.request.UpdateOrderStatusRequest;
import com.bookstore.dto.response.OrderResponse;
import com.bookstore.entity.Book;
import com.bookstore.entity.Order;
import com.bookstore.entity.User;
import com.bookstore.enums.Genre;
import com.bookstore.enums.OrderStatus;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.mapper.OrderMapper;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.OrderItemRepository;
import com.bookstore.repository.OrderRepository;
import com.bookstore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    private UpdateOrderStatusRequest updateRequest;

    private User user;
    private Book book;
    private Order order;
    private PlaceOrderRequest request;
    private OrderResponse response;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setId(1L);
        user.setEmail("deepali@gmail.com");

        book = new Book();
        book.setId(2L);
        book.setTitle("Spring Boot");
        book.setPrice(new BigDecimal("650"));
        book.setStockQuantity(20);
        book.setGenre(Genre.TECHNOLOGY);

        order = new Order();
        order.setId(1L);
        order.setUser(user);
        order.setTotalAmount(new BigDecimal("1300"));

        request = new PlaceOrderRequest();
        request.setBookId(2L);
        request.setQuantity(2);

        response = new OrderResponse();
        response.setId(1L);
        response.setUserId(1L);
        response.setTotalAmount(new BigDecimal("1300"));

        updateRequest = new UpdateOrderStatusRequest();
        updateRequest.setOrderStatus(OrderStatus.SHIPPED);
    }

    private void mockLoggedInUser() {

        org.springframework.security.core.Authentication authentication =
                org.mockito.Mockito.mock(
                        org.springframework.security.core.Authentication.class);

        org.springframework.security.core.context.SecurityContext context =
                org.mockito.Mockito.mock(
                        org.springframework.security.core.context.SecurityContext.class);

        org.mockito.Mockito.when(context.getAuthentication())
                .thenReturn(authentication);

        org.mockito.Mockito.when(authentication.getName())
                .thenReturn("deepali@gmail.com");

        org.springframework.security.core.context.SecurityContextHolder
                .setContext(context);
    }

    @Test
    void shouldPlaceOrderSuccessfully() {

        // Arrange
        mockLoggedInUser();

        when(userRepository.findByEmail("deepali@gmail.com"))
                .thenReturn(java.util.Optional.of(user));

        when(bookRepository.findById(2L))
                .thenReturn(java.util.Optional.of(book));

        when(orderRepository.save(any(Order.class)))
                .thenReturn(order);

        when(orderMapper.toResponse(any(Order.class)))
                .thenReturn(response);

        // Act
        OrderResponse result = orderService.placeOrder(request);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getUserId());
        assertEquals(new BigDecimal("1300"), result.getTotalAmount());

        // Verify repository interactions
        verify(userRepository).findByEmail("deepali@gmail.com");
        verify(bookRepository).findById(2L);
        verify(orderRepository).save(any(Order.class));
        verify(orderItemRepository).save(any());
        verify(bookRepository).save(book);

        // Verify stock reduced
        assertEquals(18, book.getStockQuantity());
    }

    @Test
    void shouldThrowExceptionWhenBookNotFound() {

        // Arrange
        mockLoggedInUser();

        when(userRepository.findByEmail("deepali@gmail.com"))
                .thenReturn(java.util.Optional.of(user));

        when(bookRepository.findById(2L))
                .thenReturn(java.util.Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> orderService.placeOrder(request)
        );

        assertEquals("Book not found", exception.getMessage());

        verify(userRepository).findByEmail("deepali@gmail.com");
        verify(bookRepository).findById(2L);

        verify(orderRepository, never()).save(any(Order.class));
        verify(orderItemRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenStockIsInsufficient() {

        // Arrange
        mockLoggedInUser();

        request.setQuantity(25);

        when(userRepository.findByEmail("deepali@gmail.com"))
                .thenReturn(java.util.Optional.of(user));

        when(bookRepository.findById(2L))
                .thenReturn(java.util.Optional.of(book));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderService.placeOrder(request)
        );

        assertEquals("Insufficient stock available", exception.getMessage());

        verify(orderRepository, never()).save(any(Order.class));
        verify(orderItemRepository, never()).save(any());
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void shouldReturnMyOrders() {

        // Arrange
        mockLoggedInUser();

        when(userRepository.findByEmail("deepali@gmail.com"))
                .thenReturn(java.util.Optional.of(user));

        when(orderRepository.findByUser(user))
                .thenReturn(java.util.List.of(order));

        when(orderMapper.toResponse(order))
                .thenReturn(response);

        // Act
        List<OrderResponse> result = orderService.getMyOrders();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(1L, result.get(0).getUserId());

        verify(userRepository).findByEmail("deepali@gmail.com");
        verify(orderRepository).findByUser(user);
    }

    @Test
    void shouldReturnOrderById() {

        // Arrange
        mockLoggedInUser();

        order.setUser(user);

        when(userRepository.findByEmail("deepali@gmail.com"))
                .thenReturn(Optional.of(user));

        when(orderRepository.findById(1L))
                .thenReturn(Optional.of(order));

        when(orderMapper.toResponse(order))
                .thenReturn(response);

        // Act
        OrderResponse result = orderService.getOrderById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getUserId());

        verify(userRepository).findByEmail("deepali@gmail.com");
        verify(orderRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenOrderBelongsToAnotherUser() {

        // Arrange
        mockLoggedInUser();

        User anotherUser = new User();
        anotherUser.setId(2L);

        order.setUser(anotherUser);

        when(userRepository.findByEmail("deepali@gmail.com"))
                .thenReturn(Optional.of(user));

        when(orderRepository.findById(1L))
                .thenReturn(Optional.of(order));

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> orderService.getOrderById(1L)
        );

        assertEquals("Order not found", exception.getMessage());

        verify(userRepository).findByEmail("deepali@gmail.com");
        verify(orderRepository).findById(1L);
        verify(orderMapper, never()).toResponse(any());
    }

    @Test
    void shouldUpdateOrderStatusSuccessfully() {

        // Arrange
        when(orderRepository.findById(1L))
                .thenReturn(Optional.of(order));

        when(orderRepository.save(order))
                .thenReturn(order);

        when(orderMapper.toResponse(order))
                .thenReturn(response);

        // Act
        OrderResponse result =
                orderService.updateOrderStatus(1L, updateRequest);

        // Assert
        assertNotNull(result);

        assertEquals(OrderStatus.SHIPPED, order.getOrderStatus());

        verify(orderRepository).findById(1L);
        verify(orderRepository).save(order);
        verify(orderMapper).toResponse(order);
    }
}