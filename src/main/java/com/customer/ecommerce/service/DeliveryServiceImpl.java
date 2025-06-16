package com.customer.ecommerce.service;
import com.customer.ecommerce.dao.DeliveryMapper;
import com.customer.ecommerce.model.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

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
    public Delivery getDeliveryByOrderId(Long orderId) {
        return deliveryMapper.findByOrderId(orderId);
    }
}