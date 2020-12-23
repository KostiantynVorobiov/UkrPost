package com.products.test.service.impl;

import com.products.test.model.Discount;
import com.products.test.model.Product;
import com.products.test.model.dto.ProductRequestDto;
import com.products.test.model.dto.ProductResponseDto;
import com.products.test.repository.DiscountRepository;
import com.products.test.repository.ProductRepository;
import com.products.test.service.ProductService;
import com.products.test.service.mapper.ProductMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final DiscountRepository discountRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper,
                              DiscountRepository discountRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.discountRepository = discountRepository;
    }

    @Override
    public Product add(ProductRequestDto productRequestDto) {
        Product product = productMapper.mapToModel(productRequestDto);
        Discount discount = new Discount();
        if (product.getDiscount().getAmountOfDiscount() == 0) {
            discount.setAmountOfDiscount(0);
        }
        discount.setAmountOfDiscount(product.getDiscount().getAmountOfDiscount());
        discountRepository.save(discount);
        product.setDiscount(discount);
        return productRepository.save(product);
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> findByCategory(String category) {
        return productRepository.findByCategory(category).stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Product get(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find product by id: " + id));
        return product;
    }

    @Override
    public void update(Long id, Integer discount) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find product by id: " + id));
        product.getDiscount().setAmountOfDiscount(discount);
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
