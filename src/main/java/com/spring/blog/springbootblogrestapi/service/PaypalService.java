package com.spring.blog.springbootblogrestapi.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PaypalService {
    Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl
    ) throws PayPalRESTException;

    Payment executePayment(
            String paymentId,
            String payerId
    ) throws PayPalRESTException;
}
