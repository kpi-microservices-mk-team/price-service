package com.example.priceservice.controllers.rest;

import com.example.priceservice.controllers.rest.dto.ProductPriceDto;
import com.example.priceservice.controllers.rest.dto.ProductPricePostDto;
import com.example.priceservice.controllers.rest.mapper.ProductPriceMapper;
import com.example.priceservice.repository.ProductPriceRepository;
import com.example.priceservice.services.ProductPriceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product-price")
public class ProductPriceController {

    private final ProductPriceProvider productPriceProvider;
    private final ProductPriceRepository productPriceRepository;

    @GetMapping("/{productId}/{currencyCode}")
    public ProductPriceDto getProductPrice(@PathVariable("productId") Integer productId, @PathVariable("currencyCode") String currency) {
        final var productPrice = productPriceProvider.provideProductPrice(productId, currency);
        return ProductPriceMapper.toWebProductPrice(productPrice);
    }

    @PostMapping("/{currencyCode}")
    public List<ProductPriceDto> getProductPrice(@PathVariable("currencyCode") String currency, @RequestBody List<Integer> productIds) {
        final var productPrices = productPriceProvider.provideAllProductPrices(productIds, currency);
        return ProductPriceMapper.toWebProductPriceList(productPrices);
    }

    @PostMapping("/{productId}/{currencyCode}")
    public void saveProductPrice(
            @PathVariable("productId") Integer productId,
            @PathVariable("currencyCode") String currency,
            @RequestBody ProductPricePostDto productPricePostDto
    ) {
        //final var persistenceProductPrice = ProductPriceMapper.toPersistenceProductPrice(productPricePostDto);
    }
}
