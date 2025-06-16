package com.customer.ecommerce.service;
import com.customer.ecommerce.dao.ReviewMapper;
import com.customer.ecommerce.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewMapper reviewMapper;
    @Override
    public Review createReview(Review review) {
        // 在这里可以添加逻辑，比如检查用户是否真的购买过此商品
        review.setCreatedAt(LocalDateTime.now());
        reviewMapper.insert(review);
        return review;
    }
}