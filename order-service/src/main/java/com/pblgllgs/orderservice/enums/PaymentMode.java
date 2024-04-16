package com.pblgllgs.orderservice.enums;

import lombok.Getter;

@Getter
public enum PaymentMode {

    CASH("cash"),
    DEBIT_CARD("debit-card"),
    CREDIT_CARD("credit-card");

    private final String value;

    PaymentMode(String value) {
        this.value = value;
    }
}
