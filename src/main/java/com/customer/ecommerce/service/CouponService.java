package com.customer.ecommerce.service;
import com.customer.ecommerce.model.Coupon;
import java.util.List;

public interface CouponService {
    List<Coupon> getAvailableCouponsByCustomerId(Long customerId);
    void assignCouponToCustomer(Long customerId, Long couponId);
    Coupon createCoupon(Coupon coupon);
    void deleteCoupon(Long id);
    Coupon getCouponById(Long id);
    Coupon updateCoupon(Long id, Coupon coupon);
}