package com.evliion.ev.config;

import com.evliion.ev.controller.RestTemplateResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
  @Bean
  public RestTemplate getRestTemplate(RestTemplateBuilder builder, RestTemplateResponseErrorHandler errorHandler) {
    RestTemplate restTemplate = builder
        .errorHandler(errorHandler)
        .build();
    return restTemplate;
  }
}
