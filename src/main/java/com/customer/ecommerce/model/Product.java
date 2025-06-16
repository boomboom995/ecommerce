package com.customer.ecommerce.model;

import lombok.Data;
import java.util.List;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Long viewCount;
    private Long categoryId; // 直接使用ID

    // 关联对象，用于结果映射
    private ProductCategory category;
    private List<ProductImage> images;
    private List<ProductVariant> variants;
}