package com.pblgllgs.orderservice.dto;

import java.time.Instant;

public record PaymentDetails(
        Long id,
        Instant paymentDate,
        String paymentMode,
        String paymentStatus,
        Double amount,
        Long orderId
) {
}
