package com.customer.ecommerce.model.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductRankDTO {
    private Long productId;
    private String productName;
    private Long totalQuantity;
    private BigDecimal totalSales;
}