package com.example.priceservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.api.ConvertRatesApi;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyConverter {

    private final ConvertRatesApi convertRatesApi;

    public Double getConvertedValue(double value, String targetCurrency, String from) {
        log.info("Start convert rate operation.");
        final var convertedValue = convertRatesApi.convertRates(targetCurrency, from, value).getValue();
        log.info("Finish convert rate operation");
        return nonNull(convertedValue) ? convertedValue : null;
    }
}
