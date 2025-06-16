package com.customer.ecommerce.service;
import com.customer.ecommerce.model.Coupon;
import java.util.List;

public interface CouponService {
    List<Coupon> getAvailableCouponsByCustomerId(Long customerId);
}