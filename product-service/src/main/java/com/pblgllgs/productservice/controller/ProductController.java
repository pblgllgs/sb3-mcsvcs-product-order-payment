package com.pblgllgs.productservice.controller;
/*
 *
 * @author pblgl
 * Created on 15-04-2024
 *
 */

import com.pblgllgs.productservice.dto.ProductRequest;
import com.pblgllgs.productservice.dto.ProductResponse;
import com.pblgllgs.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("productId") Long productId){
        return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
    }

    @PutMapping("/reduce-quantity/{productId}")
    public ResponseEntity<Void> reduceQuantity(
            @PathVariable("productId") Long productId,
            @RequestParam int quantity
    ){
        productService.reduceQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
