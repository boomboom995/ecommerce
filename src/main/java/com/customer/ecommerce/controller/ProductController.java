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

    // 更新此方法以接收URL中的查询参数
    @GetMapping
    public R<List<Product>> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long categoryId) {

        // 将接收到的参数传递给 service 层
        List<Product> products = productService.getAllProducts(name, categoryId);
        return R.success(products);
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