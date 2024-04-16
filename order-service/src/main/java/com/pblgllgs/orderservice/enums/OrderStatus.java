package com.pblgllgs.orderservice.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    CREATED("CREATED");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }
}
