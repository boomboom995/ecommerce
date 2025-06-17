package com.customer.ecommerce.controller;

import com.customer.ecommerce.common.R;
import com.customer.ecommerce.common.exception.ResourceNotFoundException;
import com.customer.ecommerce.model.Order;
import com.customer.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map; // <--- 添加这一行导入

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public R<Order> createOrder(@Valid @RequestBody Order order) {
        Order newOrder = orderService.createOrder(order);
        return R.success(newOrder);
    }

    @GetMapping("/{id}")
    public R<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        return R.success(order);
    }

    @PutMapping("/{id}/status")
    public R<Order> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String status = payload.get("status"); // 这行代码的报错也会随之消失
        return R.success(orderService.updateOrderStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public R<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return R.success("订单删除成功");
    }
}