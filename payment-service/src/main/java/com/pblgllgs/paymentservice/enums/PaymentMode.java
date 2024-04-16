package com.pblgllgs.paymentservice.enums;

import lombok.Getter;

@Getter
public enum PaymentMode {

    CASH("CASH"),
    DEBIT_CARD("DEBIT_CARD"),
    CREDIT_CARD("CREDIT_CARD");

    private final String value;

    PaymentMode(String value) {
        this.value = value;
    }
}
