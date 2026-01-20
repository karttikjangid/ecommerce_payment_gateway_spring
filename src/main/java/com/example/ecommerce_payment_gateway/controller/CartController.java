package com.example.ecommerce_payment_gateway.controller;

import com.example.ecommerce_payment_gateway.dto.AddToCartRequestDto;
import com.example.ecommerce_payment_gateway.model.CartItem;
import com.example.ecommerce_payment_gateway.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody AddToCartRequestDto dto) {
        return cartService.addToCart(
                dto.getUserId(),
                dto.getProductId(),
                dto.getQuantity()
        );
    }

    @GetMapping("/{userId}")
    public List<CartItem> viewCart(@PathVariable String userId) {
        return cartService.getCartByUserId(userId);
    }

    @DeleteMapping("/{userId}/clear")
    public void clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
    }
}
