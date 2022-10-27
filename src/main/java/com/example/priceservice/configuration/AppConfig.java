package com.example.priceservice.configuration;

import org.openapitools.client.ApiClient;
import org.openapitools.client.api.ConvertRatesApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${currency.service.path}")
    private String currencyServicePath;

    @Bean
    public ApiClient apiClient(RestTemplateBuilder restTemplateBuilder) {
        return new ApiClient(restTemplateBuilder.build())
                .setBasePath(currencyServicePath);
    }

    @Bean
    public ConvertRatesApi convertRatesApi(ApiClient apiClient) {
        return new ConvertRatesApi(apiClient);
    }
}
