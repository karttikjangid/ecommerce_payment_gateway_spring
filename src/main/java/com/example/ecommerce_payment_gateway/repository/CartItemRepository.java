package com.example.ecommerce_payment_gateway.repository;

import com.example.ecommerce_payment_gateway.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartItemRepository extends MongoRepository<CartItem, String> {
    List<CartItem> findByUserId(String userId);

    void deleteByUserId(String userId);
}
