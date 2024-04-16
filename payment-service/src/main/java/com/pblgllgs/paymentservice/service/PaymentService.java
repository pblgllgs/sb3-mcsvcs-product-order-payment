package com.pblgllgs.paymentservice.service;

import com.pblgllgs.paymentservice.dto.OrderRequest;
import com.pblgllgs.paymentservice.dto.PaymentRequest;
import com.pblgllgs.paymentservice.dto.PaymentResponse;
import com.pblgllgs.paymentservice.entity.PaymentDetails;

public interface PaymentService {

    PaymentDetails doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetails(Long orderId);
}
