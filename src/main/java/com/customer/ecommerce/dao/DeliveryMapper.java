package com.customer.ecommerce.dao;
import com.customer.ecommerce.model.Delivery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List; // 导入List

@Mapper
public interface DeliveryMapper {
    int insert(Delivery delivery);
    // 一个订单项可能对应多个配送包裹（拆单）
    List<Delivery> findByOrderItemId(@Param("orderItemId") Long orderItemId);
    // ▼▼▼ 新增方法 ▼▼▼
    Delivery findById(@Param("id") Long id);
    int update(Delivery delivery);
    int deleteById(@Param("id") Long id);
}