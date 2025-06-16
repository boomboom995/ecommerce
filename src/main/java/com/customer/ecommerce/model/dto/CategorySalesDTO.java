package com.customer.ecommerce.model.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CategorySalesDTO {
    private String categoryName;
    private Long totalQuantity;
    private BigDecimal totalSales;
}