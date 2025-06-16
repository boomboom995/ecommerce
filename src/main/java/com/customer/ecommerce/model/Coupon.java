package com.customer.ecommerce.model;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class Coupon {
    private Long id;
    private String code;
    private String description;
    private String discountType;
    private BigDecimal discountValue;
    private BigDecimal minSpend;
    private LocalDateTime validTo;
}