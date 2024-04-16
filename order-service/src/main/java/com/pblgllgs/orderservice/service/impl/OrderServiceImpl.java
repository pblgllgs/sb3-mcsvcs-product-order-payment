package com.pblgllgs.orderservice.service.impl;
/*
 *
 * @author pblgl
 * Created on 15-04-2024
 *
 */

import com.pblgllgs.orderservice.client.ProductClient;
import com.pblgllgs.orderservice.dto.OrderRequest;
import com.pblgllgs.orderservice.entity.Order;
import com.pblgllgs.orderservice.enums.OrderStatus;
import com.pblgllgs.orderservice.repository.OrderRepository;
import com.pblgllgs.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public Long createOrder(OrderRequest orderRequest) {
        log.info("Checking stock product with id: {}", orderRequest.productId());
        productClient.reduceQuantity(orderRequest.productId(), orderRequest.quantity());
        log.info("[{}]: -> Create Order with product id: {}", OrderServiceImpl.class.getName(), orderRequest.productId());
        Order order = Order.builder()
                .productId(orderRequest.productId())
                .orderStatus(OrderStatus.CREATED.name())
                .price(orderRequest.amount())
                .orderDate(Instant.now())
                .quantity(orderRequest.quantity())
                .build();
        log.info("[{}]: -> Order Created with order id: {}", OrderServiceImpl.class.getName(), order.getOrderId());
        Order orderSaved = orderRepository.save(order);
        return orderSaved.getOrderId();
    }
}

