package com.customer.ecommerce.dao;
import com.customer.ecommerce.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ProductMapper {
    Product findById(@Param("id") Long id);
    // 更新此方法以支持过滤
    List<Product> findAll(@Param("name") String name, @Param("categoryId") Long categoryId);
    int insert(Product product);
    int update(Product product);
    int deleteById(@Param("id") Long id);
}