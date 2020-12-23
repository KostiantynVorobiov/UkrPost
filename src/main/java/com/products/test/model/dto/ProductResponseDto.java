package com.products.test.model.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String category;
    private double price;
    private int discount;
}
