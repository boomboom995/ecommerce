package com.customer.ecommerce.service;
import com.customer.ecommerce.dao.ProductMapper;
import com.customer.ecommerce.model.Product;
import com.customer.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public List<Product> getAllProducts() {
        return productMapper.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productMapper.findById(id);
    }
}