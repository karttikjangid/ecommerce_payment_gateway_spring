package com.example.ecommerce_payment_gateway.repository;

import com.example.ecommerce_payment_gateway.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
