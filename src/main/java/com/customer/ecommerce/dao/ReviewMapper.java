package com.customer.ecommerce.dao;

import com.customer.ecommerce.model.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    int insert(Review review);
    // 一个订单项只有一条评论
    Review findByOrderItemId(@Param("orderItemId") Long orderItemId);
    List<Review> findByProductId(@Param("productId") Long productId);
}