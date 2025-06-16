package com.customer.ecommerce.model;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class Delivery {
    private Long id;
    private Long orderId;
    private String carrier;
    private String trackingNumber;
    private String status;
    private LocalDateTime shippedDate;
    private LocalDateTime deliveredDate;
}