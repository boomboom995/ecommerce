package com.customer.ecommerce.service;
import com.customer.ecommerce.model.Order;

public interface OrderService {
    Order createOrder(Order order); // 创建订单
    Order getOrderById(Long id);    // 新增：根据ID获取订单详情
}