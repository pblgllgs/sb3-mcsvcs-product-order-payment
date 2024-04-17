package com.pblgllgs.orderservice.client;

import com.pblgllgs.orderservice.dto.PaymentDetails;
import com.pblgllgs.orderservice.dto.PaymentRequest;
import com.pblgllgs.orderservice.dto.PaymentResponse;
import com.pblgllgs.orderservice.exception.OrderServiceCustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentClient {
    @PostMapping("/api/v1/payments/do-payment")
    ResponseEntity<PaymentDetails> doPayment(@RequestBody PaymentRequest paymentRequest);

    @GetMapping("/api/v1/payments/{orderId}")
    ResponseEntity<PaymentResponse> getPaymentDetails(@PathVariable("orderId") Long orderId);
}
