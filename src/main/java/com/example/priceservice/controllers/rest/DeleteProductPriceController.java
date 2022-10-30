package com.example.priceservice.controllers.rest;

import com.example.priceservice.repository.ProductPriceRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.DeleteProductPriceApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeleteProductPriceController implements DeleteProductPriceApi {

    private final ProductPriceRepository productPriceRepository;

    @Override
    public ResponseEntity<Void> deleteProductPrice(Integer productId) {
        productPriceRepository.deleteById(productId);
        return ResponseEntity.ok().build();
    }
}
