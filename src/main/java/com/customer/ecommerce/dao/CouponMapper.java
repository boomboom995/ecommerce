package com.customer.ecommerce.dao;
import com.customer.ecommerce.model.Coupon;
import com.customer.ecommerce.model.CustomerCoupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CouponMapper {

    // 根据 Code 查找优惠券模板详情
    Coupon findByCode(@Param("code") String code);

    // [旧] 根据客户ID和优惠券Code查找客户持有的券，用于下单时验证
    CustomerCoupon findCustomerCoupon(@Param("customerId") Long customerId, @Param("couponCode") String couponCode);

    // [新] 查找某个客户所有可用（未使用）的优惠券
    List<Coupon> findAvailableCouponsByCustomerId(@Param("customerId") Long customerId);

    // 更新用户优惠券的使用状态
    int useCustomerCoupon(CustomerCoupon customerCoupon);
}