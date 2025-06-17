package com.customer.ecommerce.service;
import com.customer.ecommerce.model.Product;
import java.util.List;

public interface ProductService {
    // 让接口方法能接收筛选参数
    List<Product> getAllProducts(String name, Long categoryId);
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}