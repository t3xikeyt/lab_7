package com.example.lab_7;

import com.example.lab_7.Product;
import com.example.lab_7.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    // Constructor injection (best practice)
    public ProductController(ProductService service) {
        this.service = service;
    }

    // GET ALL PRODUCTS
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = service.getProductById(id);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    // CREATE PRODUCT
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = service.addProduct(product);

        return ResponseEntity
                .created(URI.create("/api/products/" + created.getId()))
                .body(created);
    }

    // UPDATE PRODUCT
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product
    ) {
        Product updated = service.updateProduct(id, product);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    // DELETE PRODUCT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = service.deleteProduct(id);

        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}