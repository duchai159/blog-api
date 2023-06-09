package com.spring.blog.springbootblogrestapi.service;

import com.spring.blog.springbootblogrestapi.payload.JwtResponseDto;
import com.spring.blog.springbootblogrestapi.payload.LoginDto;
import com.spring.blog.springbootblogrestapi.payload.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    JwtResponseDto login(LoginDto loginDto);
}
