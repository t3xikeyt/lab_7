package com.example.lab_7;

import com.example.lab_7.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    // In-memory storage
    private final Map<Long, Product> products = new HashMap<>();

    // Get all products
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    // Get product by ID
    public Product getProductById(Long id) {
        return products.get(id);
    }

    // Add new product
    public Product addProduct(Product product) {

        // If product has no ID, auto-generate one
        if (product.getId() == null) {
            product.setId(generateNextId());
        }

        products.put(product.getId(), product);
        return product;
    }

    // Update product
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = products.get(id);

        if (existing == null) {
            return null;
        }

        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());
        return existing;
    }

    // Delete product
    public boolean deleteProduct(Long id) {
        return products.remove(id) != null;
    }

    // Generate an auto-increment ID
    private Long generateNextId() {
        return products.keySet().stream()
                .max(Long::compareTo)
                .orElse(0L) + 1;
    }
}
