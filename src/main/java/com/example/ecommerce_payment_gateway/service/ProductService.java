package com.example.ecommerce_payment_gateway.service;

import com.example.ecommerce_payment_gateway.exception.ResourceNotFoundException;
import com.example.ecommerce_payment_gateway.model.Product;
import com.example.ecommerce_payment_gateway.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
}