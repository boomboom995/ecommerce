package com.customer.ecommerce.controller;

import com.customer.ecommerce.common.R;
import com.customer.ecommerce.model.Coupon;
import com.customer.ecommerce.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public R<Coupon> createCoupon(@RequestBody Coupon coupon) {
        return R.success(couponService.createCoupon(coupon));
    }

    @DeleteMapping("/{id}")
    public R<String> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return R.success("优惠券种类删除成功");
    }
    // ▼▼▼ 新增 API ▼▼▼
    @GetMapping("/{id}")
    public R<Coupon> getCouponById(@PathVariable Long id) {
        return R.success(couponService.getCouponById(id));
    }

    @PutMapping("/{id}")
    public R<Coupon> updateCoupon(@PathVariable Long id, @RequestBody Coupon coupon) {
        return R.success(couponService.updateCoupon(id, coupon));
    }
}