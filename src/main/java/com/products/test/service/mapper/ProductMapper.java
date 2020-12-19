package com.products.test.service.mapper;

import com.products.test.model.Discount;
import com.products.test.model.Product;
import com.products.test.model.dto.ProductRequestDto;
import com.products.test.model.dto.ProductResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product mapToModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setCategory(productRequestDto.getCategory());
        product.setPrice(productRequestDto.getPrice());
        Discount discount = new Discount();
        discount.setAmountOfDiscount((int) productRequestDto.getDiscount());
        product.setDiscount(discount);
        return product;
    }

    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setCategory(product.getCategory());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setDiscount(product.getDiscount().getAmountOfDiscount());
        return productResponseDto;
    }
}
