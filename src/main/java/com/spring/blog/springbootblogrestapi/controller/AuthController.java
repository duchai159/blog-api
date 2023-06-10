package com.spring.blog.springbootblogrestapi.controller;

import com.spring.blog.springbootblogrestapi.payload.JwtResponse;
import com.spring.blog.springbootblogrestapi.payload.LoginDto;
import com.spring.blog.springbootblogrestapi.payload.RegisterDto;
import com.spring.blog.springbootblogrestapi.payload.RegisterResponse;
import com.spring.blog.springbootblogrestapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(authService.register(registerDto));
    }
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }
    @GetMapping("confirm")
    public ResponseEntity<String> confirm(@RequestParam String token){
        return ResponseEntity.ok(authService.confirm(token));
    }
}
