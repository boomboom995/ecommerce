package com.customer.ecommerce.service;
import com.customer.ecommerce.dao.ProductMapper;
import com.customer.ecommerce.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    // 更新实现，把参数传给Mapper
    @Override
    public List<Product> getAllProducts(String name, Long categoryId) {
        // 不再需要这行 System.out.println("c");
        return productMapper.findAll(name, categoryId);
    }

    @Override
    public Product getProductById(Long id) {
        return productMapper.findById(id);
    }
}