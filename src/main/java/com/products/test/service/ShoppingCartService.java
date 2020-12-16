package com.products.test.service;

import com.products.test.model.Product;
import com.products.test.model.ShoppingCart;
import com.products.test.model.User;

import java.util.Optional;

public interface ShoppingCartService {

    Optional<ShoppingCart> getShoppingCartByUserId(Long userId);

    ShoppingCart addProduct(Long shoppingCartId, Long productId);
}
