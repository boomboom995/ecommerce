package com.customer.ecommerce.controller;

import com.customer.ecommerce.common.R;
import com.customer.ecommerce.model.Review;
import com.customer.ecommerce.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 用户为已完成的订单商品添加评论
    @PostMapping
    public R<Review> createReview(@RequestBody Review review) {
        return R.success(reviewService.createReview(review));
    }
    // ...
    @GetMapping("/product/{productId}")
    public R<List<Review>> getReviewsByProductId(@PathVariable Long productId) {
        return R.success(reviewService.getReviewsByProductId(productId));
    }
}