package com.customer.ecommerce.service;
import com.customer.ecommerce.dao.AnalyticsMapper;
import com.customer.ecommerce.model.dto.CategorySalesDTO;
import com.customer.ecommerce.model.dto.ProductRankDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {
    private final AnalyticsMapper analyticsMapper;

    @Override
    public List<CategorySalesDTO> getSalesByCategory() {
        return analyticsMapper.getSalesByCategory();
    }

    @Override
    public List<ProductRankDTO> getProductRanking(String type) {
        // 默认按销量排序
        if (!Objects.equals(type, "quantity")) {
            type = "sales";
        }
        return analyticsMapper.getProductRanking(type);
    }
}