package com.customer.ecommerce.service;
import com.customer.ecommerce.common.exception.ResourceNotFoundException;
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
    @Override
    public Product createProduct(Product product) {
        productMapper.insert(product);
        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productMapper.findById(id);
        if (existingProduct == null) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        product.setId(id); // 确保ID正确
        productMapper.update(product);
        return productMapper.findById(id);
    }

    @Override
    public void deleteProduct(Long id) {
        // 警告：直接删除商品可能会导致问题，如果该商品已被下单。
        // 在真实项目中，通常采用“软删除”（设置一个is_deleted标志位），
        // 或者在删除前检查是否存在关联的订单项。
        // 为简化，我们这里执行硬删除。
        productMapper.deleteById(id);
    }
}