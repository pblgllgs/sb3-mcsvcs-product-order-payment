package com.pblgllgs.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *
 * @author pblgl
 * Created on 16-04-2024
 *
 */
@RestController
public class FallbackController {

    @GetMapping("/orderServiceFallBack")
    public String orderServiceFallBack(){
        return "Order service is down please try after";
    }

    @GetMapping("/productServiceFallBack")
    public String productServiceFallBack(){
        return "Product service is down please try after";
    }

    @GetMapping("/paymentServiceFallBack")
    public String paymentServiceFallBack(){
        return "Payment service is down please try after";
    }
}
