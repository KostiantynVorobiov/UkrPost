package com.products.test.service;

import com.products.test.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userId);

    String buyProducts(Long shoppingCartId);

    ShoppingCart addProduct(Long userId, Long productId);
}
