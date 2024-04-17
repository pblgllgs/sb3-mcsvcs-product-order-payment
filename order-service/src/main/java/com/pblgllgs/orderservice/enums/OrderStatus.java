package com.pblgllgs.orderservice.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    CREATED("CREATED"),
    FAILED("FAILED"),
    INCOMPLETE("INCOMPLETE"),
    COMPLETED("COMPLETED");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }
}
