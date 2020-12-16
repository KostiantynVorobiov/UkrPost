package com.products.test.controller;

import com.products.test.model.Product;
import com.products.test.model.dto.ProductRequestDto;
import com.products.test.model.dto.ProductResponseDto;
import com.products.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product create(@RequestBody ProductRequestDto productRequestDto){
        return productService.add(productRequestDto);
    }

    @GetMapping
    public List<ProductResponseDto> getAll(@RequestParam String category){
        if (category != null && !category.isEmpty()){
            return productService.findByCategory(category);
        }
        return productService.getAll();
    }

    @PutMapping("/{id}/{discount}")
    public void update(@PathVariable Long id, @PathVariable Integer discount){
        productService.update(id, discount);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.deleteById(id);
    }
}
