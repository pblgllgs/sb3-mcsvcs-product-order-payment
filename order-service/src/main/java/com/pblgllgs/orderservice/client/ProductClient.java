package com.pblgllgs.orderservice.client;
/*
 *
 * @author pblgl
 * Created on 15-04-2024
 *
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE/api/v1/products")
public interface ProductClient {

    @PutMapping("/reduce-quantity/{productId}")
    ResponseEntity<Void> reduceQuantity(
            @PathVariable("productId") Long productId,
            @RequestParam int quantity
    );
}
