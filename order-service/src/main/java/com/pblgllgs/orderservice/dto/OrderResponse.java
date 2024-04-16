package com.pblgllgs.orderservice.dto;
/*
 *
 * @author pblgl
 * Created on 16-04-2024
 *
 */

import com.pblgllgs.orderservice.enums.PaymentMode;

public record OrderResponse(
        long orderId,
        long productId,
        int quantity,
        double amount,
        PaymentMode paymentMode,
        ProductResponse productResponse,
        PaymentResponse paymentResponse

) {
}
