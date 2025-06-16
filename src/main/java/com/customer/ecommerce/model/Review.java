package com.customer.ecommerce.model;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class Review {
    private Long id;
    private Long productId;
    private Long customerId;
    private Long orderId;
    private Integer logisticsRating;
    private Integer qualityRating;
    private Integer serviceRating;
    private String commentText;
    private LocalDateTime createdAt;
}