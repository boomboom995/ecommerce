package com.customer.ecommerce.dao;
import com.customer.ecommerce.model.Delivery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeliveryMapper {
    int insert(Delivery delivery);
    Delivery findByOrderId(@Param("orderId") Long orderId);
}