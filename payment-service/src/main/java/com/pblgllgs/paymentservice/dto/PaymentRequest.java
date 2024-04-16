package com.pblgllgs.paymentservice.dto;

import com.pblgllgs.paymentservice.enums.PaymentMode;

public record PaymentRequest(
        long orderId,
        PaymentMode paymentMode,
        double amount,
        OrderRequest orderRequest
) {
}
