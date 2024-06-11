package com.bookstore.service;

import com.bookstore.entity.JwtRequest;

import com.bookstore.entity.JwtResponse;

public interface JwtService {
    JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception;
}
