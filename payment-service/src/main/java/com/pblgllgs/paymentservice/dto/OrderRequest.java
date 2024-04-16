package com.pblgllgs.paymentservice.dto;

import com.pblgllgs.paymentservice.enums.PaymentMode;

public record OrderRequest(
        Long productId,
        int quantity,
        Double amount,
        PaymentMode paymentMode
) {
}
