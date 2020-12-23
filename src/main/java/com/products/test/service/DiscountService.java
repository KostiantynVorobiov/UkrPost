package com.products.test.service;

import com.products.test.model.Discount;
import com.products.test.model.dto.DiscountRequestDto;

public interface DiscountService {

    Discount add(DiscountRequestDto discountRequestDto);
}
