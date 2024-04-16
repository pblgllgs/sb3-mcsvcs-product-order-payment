package com.pblgllgs.orderservice.dto;

import com.pblgllgs.orderservice.enums.PaymentMode;

public record PaymentRequest(
        long orderId,
        PaymentMode paymentMode,
        double amount,
        OrderRequest orderRequest
) {
}
