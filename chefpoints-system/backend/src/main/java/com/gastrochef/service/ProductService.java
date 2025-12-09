package com.gastrochef.service;

import com.gastrochef.model.Product;
import com.gastrochef.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(String name, String description, Integer pointsCost) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Produktname darf nicht leer sein");
        }
        if (pointsCost == null || pointsCost <= 0) {
            throw new IllegalArgumentException("Punktekosten müssen größer als 0 sein");
        }

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPointsCost(pointsCost);
        product.setActive(true);

        return productRepository.save(product);
    }

    public List<Product> getAllActiveProducts() {
        return productRepository.findByActiveTrue();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, String name, String description, Integer pointsCost) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Produkt nicht gefunden"));

        if (name != null && !name.trim().isEmpty()) {
            product.setName(name);
        }
        if (description != null) {
            product.setDescription(description);
        }
        if (pointsCost != null && pointsCost > 0) {
            product.setPointsCost(pointsCost);
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Produkt nicht gefunden"));
        product.setActive(false);
        productRepository.save(product);
    }
}

