package com.spring.blog.springbootblogrestapi.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.spring.blog.springbootblogrestapi.payload.PaymentRequest;
import com.spring.blog.springbootblogrestapi.service.PaypalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PaypalController {
    private final PaypalService paypalService;

    @PostMapping("/payment/create")
    public ResponseEntity<String> createPayment(@RequestBody PaymentRequest paymentRequest) throws PayPalRESTException {
        String cancelUrl = "https://localhost:8088/payment/cancel";
        String successUrl = "https://localhost:8088/payment/success";
        Payment payment = paypalService.createPayment(
                paymentRequest.getTotal(),
                "USD",
                "paypal",
                "sale",
                paymentRequest.getDescription(),
                cancelUrl,
                successUrl
        );

        for (Links links : payment.getLinks()) {
            if (links.getRel().equals("approval_url")) {
                return ResponseEntity.ok(links.getRel());
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL_SERVER_ERROR");
    }

    @GetMapping("/payment/success")
    public ResponseEntity<String> paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("payerId") String payerId
    ) throws PayPalRESTException {
        Payment payment = paypalService.executePayment(paymentId, payerId);
        if(payment.getState().equals("approved")) {
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL_SERVER_ERROR");
    }
}
