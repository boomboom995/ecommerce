package com.customer.ecommerce.service;
import com.customer.ecommerce.dao.CouponMapper;
import com.customer.ecommerce.model.Coupon;
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
}