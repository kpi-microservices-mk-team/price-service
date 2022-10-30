package com.example.priceservice.controllers.rest;

import com.example.priceservice.controllers.rest.mapper.ProductPriceMapper;
import com.example.priceservice.repository.ProductPriceRepository;
import com.example.priceservice.services.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.PostProductPriceApi;
import org.openapitools.model.ProductPriceWithCurrencyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostProductPriceController implements PostProductPriceApi {

    private static final String USD = "usd";

    private final CurrencyConverter currencyConverter;
    private final ProductPriceRepository productPriceRepository;

    @Override
    public ResponseEntity<Void> postProductPrice(Integer productId, ProductPriceWithCurrencyDto productPriceWithCurrencyDto) {
        final var convertedValue = currencyConverter.getConvertedValue(
                productPriceWithCurrencyDto.getPrice(),
                USD,
                productPriceWithCurrencyDto.getCurrency().toLowerCase()
        );

        productPriceRepository.save(ProductPriceMapper.toPersistenceEntity(productId, convertedValue));

        return ResponseEntity.ok().build();
    }
}
