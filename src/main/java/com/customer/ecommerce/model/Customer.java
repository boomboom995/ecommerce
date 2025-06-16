package com.customer.ecommerce.model;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class Customer {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Integer age;
    private BigDecimal returnRate;
}