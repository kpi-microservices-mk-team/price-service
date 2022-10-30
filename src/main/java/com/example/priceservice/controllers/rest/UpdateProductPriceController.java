package com.example.priceservice.controllers.rest;

import com.example.priceservice.beans.ProductPrice;
import com.example.priceservice.repository.ProductPriceRepository;
import com.example.priceservice.services.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.UpdateProductPriceApi;
import org.openapitools.model.ProductPriceWithCurrencyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class UpdateProductPriceController implements UpdateProductPriceApi {

    private static final String USD = "USD";
    private final CurrencyConverter currencyConverter;
    private final ProductPriceRepository productPriceRepository;

    @Override
    public ResponseEntity<Void> updateProductPrice(Integer productId, ProductPriceWithCurrencyDto productPriceWithCurrencyDto) {
        final var convertedValue = currencyConverter.getConvertedValue(
                productPriceWithCurrencyDto.getPrice(),
                USD,
                productPriceWithCurrencyDto.getCurrency().toLowerCase()
        );

        var productPrice = productPriceRepository.findById(productId).orElseThrow(
                () -> new NoSuchElementException(String.format("Cannot find product with id: %d", productId))
        );
        productPrice.setPrice(convertedValue);
        productPriceRepository.save(productPrice);

        return ResponseEntity.ok().build();
    }
}
