package com.desafio.apilocalizacao.ApiLocalizacao.Config;

import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.jackson.JacksonDecoder;

@Configuration
public class FeignConfig {
    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder();
    }
}