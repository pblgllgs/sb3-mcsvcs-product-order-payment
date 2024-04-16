package com.pblgllgs.paymentservice.dto;

import com.pblgllgs.paymentservice.enums.PaymentMode;
import com.pblgllgs.paymentservice.enums.PaymentStatus;

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
