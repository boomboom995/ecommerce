package com.customer.ecommerce.controller;

import com.customer.ecommerce.common.R;
import com.customer.ecommerce.model.Delivery;
import com.customer.ecommerce.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    // 为订单项发货，请求体中现在需要包含 orderItemId
    @PostMapping
    public R<Delivery> createDelivery(@RequestBody Delivery delivery) {
        return R.success(deliveryService.createDelivery(delivery));
    }

    // 查询指定订单项的物流状态
    // URL从 /order/{orderId} 改为 /order-item/{orderItemId} 更符合RESTful风格
    @GetMapping("/order-item/{orderItemId}")
    public R<List<Delivery>> getDeliveriesByOrderItemId(@PathVariable Long orderItemId) {
        return R.success(deliveryService.getDeliveriesByOrderItemId(orderItemId));
    }
    // ...
// ▼▼▼ 新增 API ▼▼▼
    @PutMapping("/{id}")
    public R<Delivery> updateDelivery(@PathVariable Long id, @RequestBody Delivery delivery) {
        return R.success(deliveryService.updateDelivery(id, delivery));
    }

    @DeleteMapping("/{id}")
    public R<String> deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
        return R.success("配送信息删除成功");
    }
}