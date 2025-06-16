package com.customer.ecommerce.controller;

import com.customer.ecommerce.dto.OrderRequestDto;
import com.customer.ecommerce.model.Order;
import com.customer.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        Order newOrder = orderService.createOrder(orderRequestDto);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }
}