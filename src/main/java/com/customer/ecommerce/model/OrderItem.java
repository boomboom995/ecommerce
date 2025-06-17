package com.customer.ecommerce.model;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List; // 导入 List

@Data
public class OrderItem {
    private Long id;
    private Long orderId;
    private Long variantId;
    private Integer quantity;
    private BigDecimal price;
    private Boolean isReturned;
    
    // ▼▼▼ V2.0 新增字段 ▼▼▼
    private List<Delivery> deliveries; // 一个订单项可能对应多个包裹（物流信息）
    private Review review;             // 一个订单项对应一条评论
}