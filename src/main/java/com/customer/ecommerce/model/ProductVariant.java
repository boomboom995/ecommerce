package com.customer.ecommerce.model;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class ProductVariant {
    private Long id;
    private Long productId;
    private String attributes;
    private BigDecimal price;
    private Integer stock;
    private Long purchaseCount;
}