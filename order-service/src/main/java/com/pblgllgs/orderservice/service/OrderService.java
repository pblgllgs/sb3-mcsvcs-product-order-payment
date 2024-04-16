package com.pblgllgs.orderservice.service;

import com.pblgllgs.orderservice.dto.OrderRequest;

public interface OrderService {

    Long createOrder(OrderRequest orderRequest);
}
