package com.pblgllgs.productservice.dto;

public record ProductResponse(
        String productName,
        double price,
        int quantity
) {

}
