package com.example.priceservice.controllers.rest.mapper;

import com.example.priceservice.beans.ProductPrice;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.ProductPriceDto;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductPriceMapper {

    public static List<ProductPriceDto> toResponseDtoList(List<ProductPrice> productPrices) {
        return productPrices.stream()
                .map(ProductPriceMapper::toResponseDto)
                .toList();
    }

    public static ProductPriceDto toResponseDto(ProductPrice productPrice) {
        return new ProductPriceDto()
                .productId(productPrice.getProductId())
                .price(productPrice.getPrice());
    }

    public static ProductPrice toPersistenceEntity(Integer productId, Double convertedValue) {
        return ProductPrice.builder()
                .productId(productId)
                .price(convertedValue)
                .build();
    }
}
