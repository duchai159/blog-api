package com.spring.blog.springbootblogrestapi.service.impl;

import com.spring.blog.springbootblogrestapi.entity.Role;
import com.spring.blog.springbootblogrestapi.entity.User;
import com.spring.blog.springbootblogrestapi.exception.BlogApiException;
import com.spring.blog.springbootblogrestapi.payload.JwtResponseDto;
import com.spring.blog.springbootblogrestapi.payload.LoginDto;
import com.spring.blog.springbootblogrestapi.payload.RegisterDto;
import com.spring.blog.springbootblogrestapi.repository.RoleRepository;
import com.spring.blog.springbootblogrestapi.repository.UserRepository;
import com.spring.blog.springbootblogrestapi.security.JwtTokenProvider;
import com.spring.blog.springbootblogrestapi.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Username already");
        }
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw  new BlogApiException(HttpStatus.BAD_REQUEST, "Email already");
        }
        Role role = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User user = User.builder()
                .name(registerDto.getName())
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .roles(roles)
                .build();
        userRepository.save(user);
        return "Register successfully";
    }

    @Override
    public JwtResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return JwtResponseDto.builder()
                .accessToken(token)
                .build();
    }
}
