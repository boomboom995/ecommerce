package com.customer.ecommerce.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Delivery {
    private Long id;
    private Long orderItemId; // 修改点：从 orderId 改为 orderItemId
    private String carrier;
    private String trackingNumber;
    private String status;
    private LocalDateTime shippedDate;
    private LocalDateTime deliveredDate;
}