package com.customer.ecommerce.model;
import lombok.Data;
@Data
public class CustomerCoupon {
    private Long id;
    private Long customerId;
    private Long couponId;
    private Boolean isUsed;
}