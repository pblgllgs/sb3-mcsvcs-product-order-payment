package com.pblgllgs.productservice.dto;

public record ProductRequest(
        String productName,
        double price,
        int quantity
) {
}
