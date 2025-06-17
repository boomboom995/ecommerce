package com.customer.ecommerce.service;

import com.customer.ecommerce.common.exception.ResourceNotFoundException;
import com.customer.ecommerce.dao.CouponMapper;
import com.customer.ecommerce.model.Coupon;
import com.customer.ecommerce.model.CustomerCoupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponMapper couponMapper;

    @Override
    public List<Coupon> getAvailableCouponsByCustomerId(Long customerId) {
        return couponMapper.findAvailableCouponsByCustomerId(customerId);
    }

    @Override
    public void assignCouponToCustomer(Long customerId, Long couponId) {
        CustomerCoupon customerCoupon = new CustomerCoupon();
        customerCoupon.setCustomerId(customerId);
        customerCoupon.setCouponId(couponId);
        couponMapper.insertCustomerCoupon(customerCoupon);
    }

    // ▼▼▼ 以下是本次新增的实现代码 ▼▼▼

    @Override
    public Coupon createCoupon(Coupon coupon) {
        couponMapper.insert(coupon);
        return coupon;
    }

    @Override
    public void deleteCoupon(Long id) {
        // 在真实业务中，删除优惠券模板前应检查是否已有关联的用户领取记录
        couponMapper.deleteById(id);
    }

    @Override
    public Coupon getCouponById(Long id) {
        Coupon coupon = couponMapper.findById(id);
        if (coupon == null) {
            throw new ResourceNotFoundException("Coupon not found with id: " + id);
        }
        return coupon;
    }

    @Override
    public Coupon updateCoupon(Long id, Coupon coupon) {
        // 先确认要修改的优惠券存在
        Coupon existingCoupon = couponMapper.findById(id);
        if (existingCoupon == null) {
            throw new ResourceNotFoundException("Coupon not found with id: " + id);
        }
        coupon.setId(id); // 确保ID不会被修改
        couponMapper.update(coupon);
        return couponMapper.findById(id);
    }
}