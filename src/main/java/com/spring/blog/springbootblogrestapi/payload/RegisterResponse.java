package com.spring.blog.springbootblogrestapi.payload;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RegisterResponse {
    private Date date;
    private String confirmToken;
}
