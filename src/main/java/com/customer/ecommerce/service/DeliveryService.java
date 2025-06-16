package com.customer.ecommerce.service;
import com.customer.ecommerce.model.Delivery;

public interface DeliveryService {
    Delivery createDelivery(Delivery delivery);
    Delivery getDeliveryByOrderId(Long orderId);
}