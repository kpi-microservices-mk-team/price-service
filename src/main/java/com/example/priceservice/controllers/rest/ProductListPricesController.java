package com.example.priceservice.controllers.rest;

import com.example.priceservice.controllers.rest.mapper.ProductPriceMapper;
import com.example.priceservice.services.ProductPriceProvider;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProductListPricesApi;
import org.openapitools.model.ProductPriceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductListPricesController implements ProductListPricesApi {

    private final ProductPriceProvider productPriceProvider;

    @Override
    public ResponseEntity<List<ProductPriceDto>> getProductListPrices(String currencyCode, List<Integer> requestBody) {
        final var productPrices = productPriceProvider.provideAllProductPrices(requestBody, currencyCode.toLowerCase());
        return ResponseEntity.ok(
                ProductPriceMapper.toResponseDtoList(productPrices)
        );
    }
}
