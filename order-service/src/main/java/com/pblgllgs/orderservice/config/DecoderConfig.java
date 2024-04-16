package com.pblgllgs.orderservice.config;
/*
 *
 * @author pblgl
 * Created on 15-04-2024
 *
 */

import com.pblgllgs.orderservice.exception.decoder.CustomDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DecoderConfig {

    @Bean
    public ErrorDecoder createConfig() {
        return new CustomDecoder();
    }
}
