package com.customer.ecommerce.model;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class OrderItem {
    private Long id;
    private Long orderId;
    private Long variantId;
    private Integer quantity;
    private BigDecimal price;
    private Boolean isReturned;
}