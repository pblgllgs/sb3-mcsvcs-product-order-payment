package com.pblgllgs.orderservice.dto;

import com.pblgllgs.orderservice.enums.PaymentMode;
import com.pblgllgs.orderservice.enums.PaymentStatus;

import java.time.Instant;

public record PaymentResponse(
        long paymentId,
        long orderId,
        PaymentStatus paymentStatus,
        PaymentMode paymentMode,
        double amount,
        Instant paymentDate
) {
}
