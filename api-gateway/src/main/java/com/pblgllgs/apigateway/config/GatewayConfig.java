package com.pblgllgs.apigateway.config;
/*
 *
 * @author pblgl
 * Created on 16-04-2024
 *
 */

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(predicateSpec -> predicateSpec.path("/ORDER-SERVICE/**").uri("lb://ORDER-SERVICE"))
                .route(predicateSpec -> predicateSpec.path("/PRODUCT-SERVICE/**").uri("lb://PRODUCT-SERVICE"))
                .route(predicateSpec -> predicateSpec.path("/PAYMENT-SERVICE/**").uri("lb://PAYMENT-SERVICE"))
                .build();
    }
}
