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
    void deleteOrderItemsByOrderId(@Param("orderId") Long orderId); // 删除关联的订单项
    void deleteOrderById(@Param("orderId") Long orderId); // 删除订单
    int updateStatus(@Param("id") Long id, @Param("status") String status); // 修改订单状态
}