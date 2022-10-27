package com.example.priceservice.controllers.rest.mapper;

import com.example.priceservice.beans.ProductPrice;
import com.example.priceservice.controllers.rest.dto.ProductPriceDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductPriceMapper {

    public static List<ProductPriceDto> toWebProductPriceList(List<ProductPrice> productPriceList) {
        return productPriceList.stream()
                .map(ProductPriceMapper::toWebProductPrice)
                .toList();
    }

    public static ProductPriceDto toWebProductPrice(ProductPrice productPrice) {
        return ProductPriceDto.builder()
                .productId(productPrice.getProductId())
                .price(productPrice.getPrice())
                .build();
    }
}
