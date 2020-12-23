package com.products.test.service.impl;

import com.products.test.model.Product;
import com.products.test.model.ShoppingCart;
import com.products.test.model.User;
import com.products.test.repository.ShoppingCartRepository;
import com.products.test.service.ProductService;
import com.products.test.service.ShoppingCartService;
import com.products.test.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final int HUNDRED = 100;
    private final ProductService productService;
    private final UserService userService;
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartServiceImpl(ProductService productService,
                                   UserService userService,
                                   ShoppingCartRepository shoppingCartRepository) {
        this.productService = productService;
        this.userService = userService;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        User user = userService.get(userId);
        ShoppingCart byUser = shoppingCartRepository.findByUser(user);
        return byUser;
    }

    @Override
    public ShoppingCart addProduct(Long userId, Long productId) {
        User user = userService.get(userId);
        Product product = productService.get(productId);
        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user);
        shoppingCart.getProducts().add(product);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @Override
    public String buyProducts(Long shoppingCartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(() -> new RuntimeException("Cant find cart by id: " + shoppingCartId));
        User user = shoppingCart.getUser();
        double userCash = user.getMoneyAccount();
        List<Product> products = shoppingCart.getProducts();
        double priceForAllProducts = 0;
        for (Product prod : products) {
            double discount = prod.getDiscount().getAmountOfDiscount();
            double priceWithDiscount = (HUNDRED - discount) / HUNDRED * prod.getPrice();
            priceForAllProducts += priceWithDiscount;
        }
        if (userCash > priceForAllProducts) {
            user.setMoneyAccount(userCash - priceForAllProducts);
            userService.update(user.getId(), user);
            products.clear();
            shoppingCartRepository.save(shoppingCart);
            return "You have made a purchase for the amount: " + priceForAllProducts;
        } else {
            return "You have no money for this purchase, you need: " + priceForAllProducts
                    + ", and you have: " + userCash;
        }
    }
}
