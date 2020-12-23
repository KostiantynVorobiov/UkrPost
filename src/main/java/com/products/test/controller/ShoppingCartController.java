package com.products.test.controller;

import com.products.test.model.ShoppingCart;
import com.products.test.model.dto.ShoppingCartResponseDto;
import com.products.test.service.ShoppingCartService;
import com.products.test.service.mapper.ShoppingCartMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartMapper cartMapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  ShoppingCartMapper cartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.cartMapper = cartMapper;
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(@RequestParam Long userId) {
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        return cartMapper.mapToDto(shoppingCart);
    }

    @PostMapping("/addProduct")
    public ShoppingCart addProduct(@RequestParam Long userId, @RequestParam Long productId) {
        return shoppingCartService.addProduct(userId, productId);
    }

    @GetMapping("/pay/{cartId}")
    public String pay(@PathVariable Long cartId) {
        return shoppingCartService.buyProducts(cartId);
    }
}
