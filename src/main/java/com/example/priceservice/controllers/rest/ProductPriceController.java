package com.example.priceservice.controllers.rest;

import com.example.priceservice.controllers.rest.mapper.ProductPriceMapper;
import com.example.priceservice.services.ProductPriceProvider;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProductPriceApi;
import org.openapitools.model.ProductPriceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductPriceController implements ProductPriceApi {

    private final ProductPriceProvider productPriceProvider;

    @Override
    public ResponseEntity<ProductPriceDto> getProductPrice(Integer productId, String currencyCode) {
        final var productPrice = productPriceProvider.provideProductPrice(productId, currencyCode.toLowerCase());
        return ResponseEntity.ok(ProductPriceMapper.toResponseDto(productPrice));
    }
}
