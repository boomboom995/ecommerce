package com.customer.ecommerce.service;
import com.customer.ecommerce.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
}