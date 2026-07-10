package com.bookstore.service.impl;

import com.bookstore.dto.request.LoginRequest;
import com.bookstore.dto.request.RegisterRequest;
import com.bookstore.dto.response.AuthResponse;
import com.bookstore.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthResponse register(RegisterRequest request) {

        return new AuthResponse(
                null,
                "Registration will be implemented next"
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        return new AuthResponse(
                null,
                "Login will be implemented next"
        );
    }

}