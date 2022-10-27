package com.example.priceservice.controllers.rest.dto;

import lombok.Builder;

@Builder
public record ProductPriceDto(int productId, double price) {
}
