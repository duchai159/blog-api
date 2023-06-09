package com.spring.blog.springbootblogrestapi.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponseDto {
    private String accessToken;
    private final String tokenType = "Bearer";
}
