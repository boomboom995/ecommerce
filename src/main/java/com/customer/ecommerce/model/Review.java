package com.customer.ecommerce.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Review {
    private Long id;
    private Long orderItemId; // 修改点：替换了 productId 和 orderId
    private Long customerId;
    private Integer logisticsRating;
    private Integer qualityRating;
    private Integer serviceRating;
    private String commentText;
    private LocalDateTime createdAt;
}