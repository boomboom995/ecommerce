package com.customer.ecommerce.service;
import com.customer.ecommerce.model.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(Review review);
    List<Review> getReviewsByProductId(Long productId);

    Review getReviewByDeliveryId(Long deliveryId);
}