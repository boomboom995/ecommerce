package com.customer.ecommerce.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Long customerId;
    private String shippingAddress; // 新增字段
    private BigDecimal originalAmount;
    private BigDecimal discountAmount;
    private BigDecimal totalAmount;
    private String couponCode;
    private String status;
    private LocalDateTime orderDate;
    private Customer customer;
    private List<OrderItem> items;
}