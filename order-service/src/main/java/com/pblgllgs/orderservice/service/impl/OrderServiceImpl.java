package com.pblgllgs.orderservice.service.impl;
/*
 *
 * @author pblgl
 * Created on 15-04-2024
 *
 */

import com.pblgllgs.orderservice.client.PaymentClient;
import com.pblgllgs.orderservice.client.ProductClient;
import com.pblgllgs.orderservice.dto.*;
import com.pblgllgs.orderservice.entity.Order;
import com.pblgllgs.orderservice.enums.OrderStatus;
import com.pblgllgs.orderservice.enums.PaymentMode;
import com.pblgllgs.orderservice.enums.PaymentStatus;
import com.pblgllgs.orderservice.exception.OrderServiceException;
import com.pblgllgs.orderservice.repository.OrderRepository;
import com.pblgllgs.orderservice.service.OrderService;
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
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;

    @Override
    public Long createOrder(OrderRequest orderRequest) {
        log.info("Checking stock product with id: {}", orderRequest.productId());
        log.info("[{}]: -> Create Order with product id: {}", OrderServiceImpl.class.getName(), orderRequest.productId());
        Order order = Order.builder()
                .productId(orderRequest.productId())
                .orderStatus(OrderStatus.CREATED.name())
                .price(orderRequest.amount())
                .orderDate(Instant.now())
                .quantity(orderRequest.quantity())
                .build();
        log.info("[{}]: -> Order Created with order id: {}", OrderServiceImpl.class.getName(), order.getOrderId());
        Order orderSavedCreated = orderRepository.save(order);
        PaymentRequest paymentRequest = new PaymentRequest(
                orderSavedCreated.getOrderId(),
                orderRequest.paymentMode(),
                orderRequest.amount(),
                orderRequest
        );
        log.info(
                "[{}]: -> Create Payment request with request id: {}",
                OrderServiceImpl.class.getName(),
                orderRequest.productId()
        );
        try {
            PaymentDetails paymentDetails = paymentClient.doPayment(paymentRequest).getBody();
            assert paymentDetails != null;
            if (paymentDetails.paymentStatus().equals(PaymentStatus.SUCCESS.getValue())) {
                productClient.reduceQuantity(orderRequest.productId(), orderRequest.quantity());
                log.info(
                        "[{}]: -> Payment request with order id: {}",
                        OrderServiceImpl.class.getName(),
                        orderSavedCreated.getOrderId()
                );
                orderSavedCreated.setOrderStatus(OrderStatus.COMPLETED.name());
            }else {
                orderSavedCreated.setOrderStatus(OrderStatus.FAILED.name());
            }
        } catch (Exception ex) {
            log.info(
                    "[{}]: -> Order created failure with order id: {}",
                    OrderServiceImpl.class.getName(),
                    orderSavedCreated.getOrderId()
            );
            orderSavedCreated.setOrderStatus(OrderStatus.FAILED.name());
        }
        log.info(
                "[{}]: -> Order created Successfully with order id: {}",
                OrderServiceImpl.class.getName(),
                orderSavedCreated.getOrderId()
        );
        Order orderSavedFinally = orderRepository.save(orderSavedCreated);
        return orderSavedFinally.getOrderId();
    }

    @Override
    public OrderResponse getOrderDetails(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderServiceException("Order not found", HttpStatus.NOT_FOUND));
        ProductResponse productResponse = productClient.getProductById(order.getProductId()).getBody();
        PaymentResponse paymentResponse = paymentClient.getPaymentDetails(order.getOrderId()).getBody();
        return new OrderResponse(
                order.getOrderId(),
                order.getProductId(),
                order.getQuantity(),
                order.getPrice() * order.getQuantity(),
                paymentResponse.paymentMode(),
                productResponse,
                paymentResponse
        );
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderServiceException(
                "Order not Found",
                HttpStatus.NOT_FOUND
        ));
    }

}

