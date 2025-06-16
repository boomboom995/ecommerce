package com.customer.ecommerce.dao;
import com.customer.ecommerce.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ProductMapper {
    Product findById(@Param("id") Long id);
    List<Product> findAll();
    int insert(Product product);
}