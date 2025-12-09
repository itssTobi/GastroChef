package com.gastrochef.controller;

import com.gastrochef.dto.ApiResponse;
import com.gastrochef.dto.CreateProductRequest;
import com.gastrochef.model.Product;
import com.gastrochef.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts(@RequestParam(required = false) Long userId) {
        try {
            List<Product> products = productService.getAllActiveProducts();
            return ResponseEntity.ok(new ApiResponse(true, "Produkte abgerufen", products));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse(false, "Fehler beim Abrufen der Produkte: " + e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProduct(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Produkt nicht gefunden"));
            return ResponseEntity.ok(new ApiResponse(true, "Produkt gefunden", product));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@RequestBody CreateProductRequest request) {
        try {
            Product product = productService.createProduct(
                request.getName(),
                request.getDescription(),
                request.getPointsCost()
            );
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Produkt erstellt", product));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProduct(
        @PathVariable Long id,
        @RequestBody CreateProductRequest request
    ) {
        try {
            Product product = productService.updateProduct(
                id,
                request.getName(),
                request.getDescription(),
                request.getPointsCost()
            );
            return ResponseEntity.ok(new ApiResponse(true, "Produkt aktualisiert", product));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok(new ApiResponse(true, "Produkt gelöscht", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse(false, e.getMessage(), null));
        }
    }
}

