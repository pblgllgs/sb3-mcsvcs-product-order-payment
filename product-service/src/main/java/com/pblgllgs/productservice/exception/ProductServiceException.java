package com.pblgllgs.productservice.exception;
/*
 *
 * @author pblgl
 * Created on 15-04-2024
 *
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductServiceException extends RuntimeException {

    private final HttpStatus errorCode;
    public ProductServiceException(String message, HttpStatus errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
