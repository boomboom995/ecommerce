package com.customer.ecommerce.service;
import com.customer.ecommerce.dao.OrderRequestDto;
import com.customer.ecommerce.model.Order;

public interface OrderService {
    Order createOrder(OrderRequestDto orderRequestDto);
}