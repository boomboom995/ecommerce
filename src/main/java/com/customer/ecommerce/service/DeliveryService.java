package com.customer.ecommerce.service;
import com.customer.ecommerce.model.Delivery;
import java.util.List;

public interface DeliveryService {
    Delivery createDelivery(Delivery delivery);
    List<Delivery> getDeliveriesByOrderItemId(Long orderItemId);
    Delivery updateDelivery(Long id, Delivery delivery);
    void deleteDelivery(Long id);// 修改方法
}