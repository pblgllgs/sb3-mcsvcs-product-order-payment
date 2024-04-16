package com.pblgllgs.paymentservice.service.impl;
/*
 *
 * @author pblgl
 * Created on 16-04-2024
 *
 */

import com.pblgllgs.paymentservice.client.ProductClient;
import com.pblgllgs.paymentservice.dto.PaymentRequest;
import com.pblgllgs.paymentservice.dto.PaymentResponse;
import com.pblgllgs.paymentservice.dto.ProductResponse;
import com.pblgllgs.paymentservice.entity.PaymentDetails;
import com.pblgllgs.paymentservice.enums.PaymentMode;
import com.pblgllgs.paymentservice.enums.PaymentStatus;
import com.pblgllgs.paymentservice.exception.PaymentServiceException;
import com.pblgllgs.paymentservice.repository.PaymentRepository;
import com.pblgllgs.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ProductClient productClient;

    @Override
    public PaymentDetails doPayment(PaymentRequest paymentRequest) {
        ProductResponse productResponse  = productClient.getProductById(paymentRequest.orderRequest().productId()).getBody();
        log.info("[{}] -> Do Payment with order id: {}", PaymentServiceImpl.class.getName(), paymentRequest.orderId());
        PaymentDetails paymentDetails;
        PaymentDetails paymentDetailsSaved;
        assert productResponse != null;
        if (
                (paymentRequest.orderRequest().amount()/paymentRequest.orderRequest().quantity()) == productResponse.price()
                &&
                paymentRequest.orderRequest().quantity() <= productResponse.quantity()
        ) {
            paymentDetails = PaymentDetails.builder()
                    .paymentStatus(PaymentStatus.SUCCESS.getValue())
                    .paymentMode(paymentRequest.paymentMode().getValue())
                    .paymentDate(Instant.now())
                    .orderId(paymentRequest.orderId())
                    .amount(paymentRequest.amount())
                    .build();
            paymentDetailsSaved = paymentRepository.save(paymentDetails);
        } else {
            paymentDetails = PaymentDetails.builder()
                    .paymentStatus(PaymentStatus.FAILURE.getValue())
                    .paymentMode(paymentRequest.paymentMode().getValue())
                    .paymentDate(Instant.now())
                    .orderId(paymentRequest.orderId())
                    .amount(paymentRequest.amount())
                    .build();
            paymentDetailsSaved = paymentRepository.save(paymentDetails);
        }
        log.info("[{}] -> Payment done with payment id: {}", PaymentServiceImpl.class.getName(), paymentDetailsSaved.getId());
        return paymentDetailsSaved;
    }

    @Override
    public PaymentResponse getPaymentDetails(Long orderId) {
        PaymentDetails paymentDetails = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new PaymentServiceException("No payments with order id: " + orderId, HttpStatus.NOT_FOUND));
        return new PaymentResponse(
                paymentDetails.getId(),
                paymentDetails.getOrderId(),
                PaymentStatus.valueOf(paymentDetails.getPaymentStatus()),
                PaymentMode.valueOf(paymentDetails.getPaymentMode()),
                paymentDetails.getAmount(),
                Instant.now()
        );
    }
}
