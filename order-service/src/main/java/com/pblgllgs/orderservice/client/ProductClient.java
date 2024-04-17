package com.pblgllgs.orderservice.client;
/*
 *
 * @author pblgl
 * Created on 15-04-2024
 *
 */

import com.pblgllgs.orderservice.dto.ProductResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {

    @PutMapping("/api/v1/products/reduce-quantity/{productId}")
    ResponseEntity<Void> reduceQuantity(
            @PathVariable("productId") Long productId,
            @RequestParam int quantity
    );

    @GetMapping("/api/v1/products/{productId}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable("productId") Long productId);
}

