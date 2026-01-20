package com.example.ecommerce_payment_gateway.service;

import com.example.ecommerce_payment_gateway.model.CartItem;
import com.example.ecommerce_payment_gateway.model.Product;
import com.example.ecommerce_payment_gateway.repository.CartItemRepository;
import com.example.ecommerce_payment_gateway.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;

    public CartItem addToCart(String userId, String productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product is not found!!"));
        CartItem item = new CartItem();
        item.setUserId(userId);
        item.setProductId(product.getId());
        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }

    public List<CartItem> getCartByUserId(String userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}