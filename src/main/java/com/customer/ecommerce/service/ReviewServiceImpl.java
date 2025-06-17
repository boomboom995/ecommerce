package com.customer.ecommerce.service;

// 确保所有导入都是正确的
import com.customer.ecommerce.dao.DeliveryMapper;
import com.customer.ecommerce.dao.ReviewMapper;
import com.customer.ecommerce.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final DeliveryMapper deliveryMapper;

    @Override
    public Review createReview(Review review) {
        review.setCreatedAt(LocalDateTime.now());
        reviewMapper.insert(review);
        return review;
    }

    @Override
    public List<Review> getReviewsByProductId(Long productId) {
        // 这行代码现在应该可以正常工作了
        return reviewMapper.findByProductId(productId);
    }

    @Override
    public Review getReviewByDeliveryId(Long deliveryId) {
        // 此方法的逻辑待定，暂时保持不变
        return null;
    }
}