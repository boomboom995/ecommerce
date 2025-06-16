package com.customer.ecommerce.service;
import com.customer.ecommerce.model.dto.CategorySalesDTO;
import com.customer.ecommerce.model.dto.ProductRankDTO;
import java.util.List;

public interface AnalyticsService {
    List<CategorySalesDTO> getSalesByCategory();
    List<ProductRankDTO> getProductRanking(String type);
}