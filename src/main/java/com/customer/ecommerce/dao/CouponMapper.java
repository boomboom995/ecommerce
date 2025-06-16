package com.customer.ecommerce.dao;
import com.customer.ecommerce.model.Coupon;
import com.customer.ecommerce.model.CustomerCoupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CouponMapper {
    CustomerCoupon findCustomerCoupon(@Param("customerId") Long customerId, @Param("couponCode") String couponCode);
    int useCustomerCoupon(CustomerCoupon customerCoupon);
}