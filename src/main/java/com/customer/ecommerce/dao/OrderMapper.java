package com.customer.ecommerce.dao;
import com.customer.ecommerce.model.Order;
import com.customer.ecommerce.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface OrderMapper {
    int insertOrder(Order order);
    int insertOrderItems(@Param("items") List<OrderItem> items);
    Order findById(@Param("id") Long id);
}