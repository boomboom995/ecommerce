package com.customer.ecommerce.dao;
import com.customer.ecommerce.model.Review;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    int insert(Review review);
}