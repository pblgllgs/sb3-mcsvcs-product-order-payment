package com.pblgllgs.orderservice.dto;

public record ProductResponse(
        String productName,
        double price,
        int quantity
) {

}
