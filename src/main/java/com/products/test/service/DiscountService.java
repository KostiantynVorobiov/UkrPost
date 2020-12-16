package com.products.test.service;

import com.products.test.model.Discount;
import com.products.test.model.Product;
import com.products.test.model.dto.DiscountRequestDto;
import com.products.test.model.dto.ProductRequestDto;

public interface DiscountService {

    Discount add(DiscountRequestDto discountRequestDto);
}
