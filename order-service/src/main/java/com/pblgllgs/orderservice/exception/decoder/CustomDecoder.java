package com.pblgllgs.orderservice.exception.decoder;
/*
 *
 * @author pblgl
 * Created on 15-04-2024
 *
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pblgllgs.orderservice.exception.ErrorResponse;
import com.pblgllgs.orderservice.exception.OrderServiceCustomException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class CustomDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ErrorResponse errorResponse = mapper.readValue(response.body().asInputStream(), ErrorResponse.class);
            return new OrderServiceCustomException(errorResponse.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            throw new OrderServiceCustomException("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
