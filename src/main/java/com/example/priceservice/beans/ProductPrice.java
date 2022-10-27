package com.example.priceservice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "productId")
public class ProductPrice {

    @Id
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "price_in_usd")
    private double price;
}
