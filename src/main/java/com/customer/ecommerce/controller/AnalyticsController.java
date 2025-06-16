package com.customer.ecommerce.controller;

import com.customer.ecommerce.common.R;
import com.customer.ecommerce.model.dto.CategorySalesDTO;
import com.customer.ecommerce.model.dto.ProductRankDTO;
import com.customer.ecommerce.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    // 获取各品类销量统计
    @GetMapping("/sales-by-category")
    public R<List<CategorySalesDTO>> getSalesByCategory() {
        return R.success(analyticsService.getSalesByCategory());
    }

    // 获取商品排行榜
    @GetMapping("/rankings")
    public R<List<ProductRankDTO>> getProductRanking(@RequestParam(defaultValue = "sales") String type) {
        return R.success(analyticsService.getProductRanking(type));
    }
}