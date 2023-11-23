package com.spring.blog.springbootblogrestapi.payload;

import com.spring.blog.springbootblogrestapi.annotation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterDto {
    private String name;
    @NotEmpty
    private String username;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Password
    private String password;
}
