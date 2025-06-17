package com.customer.ecommerce.dao;

import com.customer.ecommerce.model.dto.CategorySalesDTO;
import com.customer.ecommerce.model.dto.ProductRankDTO;
import com.customer.ecommerce.model.dto.ReviewStatsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnalyticsMapper {

    List<CategorySalesDTO> getSalesByCategory();

    List<ProductRankDTO> getProductRanking(@Param("type") String type);
    // ...
    ReviewStatsDTO getReviewStatsByProductId(@Param("productId") Long productId);
}