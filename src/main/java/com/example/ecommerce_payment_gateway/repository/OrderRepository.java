package com.example.ecommerce_payment_gateway.repository;

import com.example.ecommerce_payment_gateway.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUserId(String userId);
}
