package com.pblgllgs.paymentservice.controller;
/*
 *
 * @author pblgl
 * Created on 16-04-2024
 *
 */

import com.pblgllgs.paymentservice.dto.PaymentRequest;
import com.pblgllgs.paymentservice.dto.PaymentResponse;
import com.pblgllgs.paymentservice.entity.PaymentDetails;
import com.pblgllgs.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/do-payment")
    public ResponseEntity<PaymentDetails> doPayment(@RequestBody PaymentRequest paymentRequest){
        return new ResponseEntity<>(paymentService.doPayment(paymentRequest), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetails(@PathVariable("orderId") Long orderId){
        return new ResponseEntity<>(paymentService.getPaymentDetails(orderId), HttpStatus.OK);
    }
}
