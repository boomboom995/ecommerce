package com.customer.ecommerce.service;

import com.customer.ecommerce.common.exception.ResourceNotFoundException;
import com.customer.ecommerce.dao.DeliveryMapper;
import com.customer.ecommerce.model.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryMapper deliveryMapper;

    @Override
    public Delivery createDelivery(Delivery delivery) {
        delivery.setStatus("SHIPPED");
        delivery.setShippedDate(LocalDateTime.now());
        deliveryMapper.insert(delivery);
        return delivery;
    }

    @Override
    public List<Delivery> getDeliveriesByOrderItemId(Long orderItemId) {
        return deliveryMapper.findByOrderItemId(orderItemId);
    }

    // ▼▼▼ 补全缺失的方法实现 ▼▼▼

    @Override
    public Delivery updateDelivery(Long id, Delivery delivery) {
        Delivery existingDelivery = deliveryMapper.findById(id);
        if (existingDelivery == null) {
            throw new ResourceNotFoundException("Delivery not found with id: " + id);
        }
        delivery.setId(id); // 确保ID正确
        deliveryMapper.update(delivery);
        return deliveryMapper.findById(id);
    }

    @Override
    public void deleteDelivery(Long id) {
        deliveryMapper.deleteById(id);
    }
}