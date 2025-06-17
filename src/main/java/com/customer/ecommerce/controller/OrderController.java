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

    // ▼▼▼ 更新这个方法 ▼▼▼
    @PostMapping
    public R<Order> createOrder(@Valid @RequestBody Order order) {
        // 1. 先调用service创建订单
        Order createdOrder = orderService.createOrder(order);
        
        // 2. 根据新生成的ID，从数据库重新获取最完整的订单信息
        Order detailedOrder = orderService.getOrderById(createdOrder.getId());
        
        // 3. 将最完整、最准确的信息返回给用户
        return R.success(detailedOrder);
    }

    @GetMapping("/{id}")
    public R<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        return R.success(order);
    }
}