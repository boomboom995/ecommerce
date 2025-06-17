package com.customer.ecommerce.model.dto;

import lombok.Data;

@Data
public class ReviewStatsDTO {
    private Long productId;
    private long reviewCount;
    private Double avgLogisticsRating;
    private Double avgQualityRating;
    private Double avgServiceRating;
}