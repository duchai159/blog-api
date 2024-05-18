package com.spring.blog.springbootblogrestapi.payload;

import lombok.Data;

@Data
public class PaymentRequest {
    private Double total;
    private String description;
}
