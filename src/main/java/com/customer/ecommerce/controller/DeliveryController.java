package com.customer.ecommerce.controller;

import com.customer.ecommerce.common.R;
import com.customer.ecommerce.model.Delivery;
import com.customer.ecommerce.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    // 为订单发货
    @PostMapping
    public R<Delivery> createDelivery(@RequestBody Delivery delivery) {
        return R.success(deliveryService.createDelivery(delivery));
    }

    // 查询订单的物流状态
    @GetMapping("/order/{orderId}")
    public R<Delivery> getDeliveryByOrderId(@PathVariable Long orderId) {
        return R.success(deliveryService.getDeliveryByOrderId(orderId));
    }
}