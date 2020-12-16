package com.products.test.service.impl;

import com.products.test.model.Product;
import com.products.test.model.ShoppingCart;
import com.products.test.model.User;
import com.products.test.repository.ShoppingCartRepository;
import com.products.test.service.ProductService;
import com.products.test.service.ShoppingCartService;
import com.products.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ProductService productService;
    private final UserService userService;
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartServiceImpl(ProductService productService, UserService userService, ShoppingCartRepository shoppingCartRepository) {
        this.productService = productService;
        this.userService = userService;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    @Override
    public Optional<ShoppingCart> getShoppingCartByUserId(Long userId) {
        User user = userService.get(userId);
        ShoppingCart byUser = shoppingCartRepository.findByUser(user);
        return Optional.ofNullable(byUser);
    }

    @Override
    public ShoppingCart addProduct(Long shoppingCartId, Long productId) {
        ShoppingCart shoppingCart = shoppingCartRepository.getOne(shoppingCartId);
        Product product = productService.get(productId);
        shoppingCart.getProducts().add(product);
        return shoppingCartRepository.save(shoppingCart);
    }
}
