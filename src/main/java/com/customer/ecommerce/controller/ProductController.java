package com.customer.ecommerce.controller;
import com.customer.ecommerce.common.R;
import com.customer.ecommerce.common.exception.ResourceNotFoundException;
import com.customer.ecommerce.model.Product;
import com.customer.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public R<List<Product>> getAllProducts() {
        return R.success(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public R<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        return R.success(product);
    }
}