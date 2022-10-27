package com.example.priceservice.services;

import com.example.priceservice.beans.ProductPrice;
import com.example.priceservice.repository.ProductPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductPriceProvider {

    private static final String PERSISTENCE_CURRENCY = "USD";

    private final ProductPriceRepository productPriceRepository;
    private final CurrencyConverter currencyConverter;

    public ProductPrice provideProductPrice(int productId, String currency) {
        var productPrice = productPriceRepository.findById(productId)
                .orElseThrow(
                        () -> new NoSuchElementException(String.format("Cannot find product with id: %d", productId))
                );
        final var convertedPrice = currencyConverter.getConvertedValue(productPrice.getPrice(), currency, PERSISTENCE_CURRENCY);
        productPrice.setPrice(convertedPrice);

        return productPrice;
    }

    public List<ProductPrice> provideAllProductPrices(List<Integer> productIds, String currency) {
        var productPriceList = productPriceRepository.findAllById(productIds);
        return productPriceList.stream()
                .peek(productPrice -> {
                    final var convertedValue = currencyConverter.getConvertedValue(
                            productPrice.getPrice(),
                            currency,
                            PERSISTENCE_CURRENCY
                    );
                    productPrice.setPrice(convertedValue);
                })
                .toList();
    }
}
