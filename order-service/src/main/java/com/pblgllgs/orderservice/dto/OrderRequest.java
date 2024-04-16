package com.pblgllgs.orderservice.dto;

import com.pblgllgs.orderservice.enums.PaymentMode;

public record OrderRequest(
        Long productId,
        int quantity,
        Double amount,
        PaymentMode paymentMode
) {
}
