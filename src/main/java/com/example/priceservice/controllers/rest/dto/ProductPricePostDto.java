package com.example.priceservice.controllers.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductPricePostDto {

    private int productId;
    private double price;
    private String currencyCode;
}
