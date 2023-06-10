package com.spring.blog.springbootblogrestapi.service;

import com.spring.blog.springbootblogrestapi.payload.JwtResponse;
import com.spring.blog.springbootblogrestapi.payload.LoginDto;
import com.spring.blog.springbootblogrestapi.payload.RegisterDto;
import com.spring.blog.springbootblogrestapi.payload.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterDto registerDto);

    JwtResponse login(LoginDto loginDto);

    String confirm(String token);
}
