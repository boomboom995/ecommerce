package com.customer.ecommerce.model;
import lombok.Data;
import java.util.List;
@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Long viewCount;
    private Long categoryId;
    private ProductCategory category; // 用于关联查询
    private List<ProductImage> images; // 用于关联查询
    private List<ProductVariant> variants; // 用于关联查询
}