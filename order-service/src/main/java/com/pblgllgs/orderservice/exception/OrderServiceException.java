package com.pblgllgs.orderservice.exception;
/*
 *
 * @author pblgl
 * Created on 16-04-2024
 *
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderServiceException extends RuntimeException {

    private final HttpStatus errorCode;
    public OrderServiceException(String message, HttpStatus errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
