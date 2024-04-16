package com.pblgllgs.orderservice.service;

import com.pblgllgs.orderservice.dto.OrderRequest;
import com.pblgllgs.orderservice.dto.OrderResponse;
import com.pblgllgs.orderservice.entity.Order;

public interface OrderService {

    Long createOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(Long orderId);

    Order getOrder(Long orderId);
}
