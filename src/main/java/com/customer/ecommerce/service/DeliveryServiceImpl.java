package com.customer.ecommerce.service;
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
        // 这里可以增加逻辑，比如检查订单项是否存在等
        delivery.setStatus("SHIPPED");
        delivery.setShippedDate(LocalDateTime.now());
        deliveryMapper.insert(delivery);
        return delivery;
    }

    @Override
    public List<Delivery> getDeliveriesByOrderItemId(Long orderItemId) {
        return deliveryMapper.findByOrderItemId(orderItemId);
    }
}