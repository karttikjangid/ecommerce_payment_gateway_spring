package com.example.ecommerce_payment_gateway.repository;

import com.example.ecommerce_payment_gateway.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
