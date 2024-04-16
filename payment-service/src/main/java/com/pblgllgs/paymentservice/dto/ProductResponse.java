package com.pblgllgs.paymentservice.dto;

public record ProductResponse(
        String productName,
        double price,
        int quantity
) {

}
