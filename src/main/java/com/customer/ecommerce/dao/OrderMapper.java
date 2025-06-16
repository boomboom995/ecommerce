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
    // 更新此方法签名
    Order findById(@Param("id") Long id);
}