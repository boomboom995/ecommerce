package com.customer.ecommerce.service;
import com.customer.ecommerce.model.Order;

public interface OrderService {
    Order createOrder(Order order); // 直接接收 Model
}