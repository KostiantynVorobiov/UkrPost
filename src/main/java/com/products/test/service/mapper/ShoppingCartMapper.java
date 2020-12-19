package com.products.test.service.mapper;

import com.products.test.model.Product;
import com.products.test.model.ShoppingCart;
import com.products.test.model.dto.ShoppingCartResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartMapper {
    public ShoppingCartResponseDto mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto cartResponseDto = new ShoppingCartResponseDto();
        cartResponseDto.setId(shoppingCart.getId());
        cartResponseDto.setUserEmail(shoppingCart.getUser().getEmail());
        List<Long> products = shoppingCart.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList());
        cartResponseDto.setProducts(products);
        return cartResponseDto;
    }
}
