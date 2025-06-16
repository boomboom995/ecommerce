package com.customer.ecommerce.dao;
import com.customer.ecommerce.model.ProductVariant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductVariantMapper {
    ProductVariant findById(@Param("id") Long id);
    int updateStock(ProductVariant variant);
}