package com.customer.ecommerce.controller;
import com.customer.ecommerce.common.R;
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
        // 前端请求JSON示例:
        // {
        //   "customerId": 1,
        //   "couponCode": "SUMMER10",
        //   "items": [
        //     { "variantId": 101, "quantity": 2 },
        //     { "variantId": 105, "quantity": 1 }
        //   ]
        // }
        Order newOrder = orderService.createOrder(order);
        return R.success(newOrder);
    }
}