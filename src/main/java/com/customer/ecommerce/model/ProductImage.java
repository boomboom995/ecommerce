package com.customer.ecommerce.model;
import lombok.Data;
@Data
public class ProductImage {
    private Long id;
    private Long productId;
    private String imageUrl;
}