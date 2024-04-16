package com.pblgllgs.paymentservice.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {

    SUCCESS("SUCCESS"),
    FAILURE("FAILURE");

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }
}
