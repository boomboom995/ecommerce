package com.customer.ecommerce.controller;
import com.customer.ecommerce.common.R;
import com.customer.ecommerce.common.exception.ResourceNotFoundException;
import com.customer.ecommerce.model.Order;
import com.customer.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    // 新增：根据ID获取订单详情的接口
    @GetMapping("/{id}")
    public R<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        return R.success(order);
    }
}