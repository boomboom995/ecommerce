//package com.customer.ecommerce.model;
//
//import lombok.Data;
//import java.util.List;
//
//@Data
//public class Product {
//    private Long id;
//    private String name;
//    private String description;
//    private Long viewCount;
//    private Long categoryId; // 直接使用ID
//
//    // 关联对象，用于结果映射
//    private ProductCategory category;
//    private List<ProductImage> images;
//    private List<ProductVariant> variants;
//}

package com.customer.ecommerce.model;

import lombok.Data;
import java.util.ArrayList; // 导入 ArrayList
import java.util.List;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Long viewCount;
    private Long categoryId;

    private ProductCategory category;

    // ==================== 修改点 START ====================
    // 直接初始化列表，防止它们为 null
    private List<ProductImage> images = new ArrayList<>();
    private List<ProductVariant> variants = new ArrayList<>();
    // ===================== 修改点 END =====================
}