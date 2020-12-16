package com.products.test.service;

import com.products.test.model.Product;
import com.products.test.model.dto.ProductRequestDto;
import com.products.test.model.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    Product add(ProductRequestDto productRequestDto);

    List<ProductResponseDto> getAll();

    List<ProductResponseDto> findByCategory (String category);

    Product get(Long id);

    void update(Long id, Integer discount);

    void deleteById(Long id);
}
