package com.customer.ecommerce.service;

import com.customer.ecommerce.common.enums.DiscountType;
import com.customer.ecommerce.common.exception.ResourceNotFoundException;
import com.customer.ecommerce.dao.*;
import com.customer.ecommerce.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;
    private final ProductVariantMapper variantMapper;
    private final CouponMapper couponMapper; // 用于获取优惠券详情

    @Override
    public Order getOrderById(Long id) {
        return orderMapper.findById(id);
    }

    @Override
    @Transactional
    public Order createOrder(Order orderRequest) {
        Customer customer = customerMapper.findById(orderRequest.getCustomerId());
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found with id: " + orderRequest.getCustomerId());
        }

        Order newOrder = new Order();
        newOrder.setCustomerId(customer.getId());
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setStatus("PENDING_PAYMENT"); // 状态更新为待支付

        BigDecimal originalAmount = BigDecimal.ZERO;
        List<OrderItem> requestedItems = orderRequest.getItems();
        if (requestedItems == null || requestedItems.isEmpty()) {
            throw new IllegalArgumentException("Order items cannot be empty.");
        }

        for (OrderItem itemDto : requestedItems) {
            ProductVariant variant = variantMapper.findById(itemDto.getVariantId());
            if (variant == null) {
                throw new ResourceNotFoundException("Product variant not found: " + itemDto.getVariantId());
            }
            if (variant.getStock() < itemDto.getQuantity()) {
                throw new IllegalStateException("Stock not enough for product variant: " + variant.getId());
            }

            itemDto.setPrice(variant.getPrice());
            originalAmount = originalAmount.add(variant.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));
            variant.setStock(variant.getStock() - itemDto.getQuantity());
            variantMapper.updateStock(variant);
        }

        newOrder.setOriginalAmount(originalAmount);
        newOrder.setTotalAmount(originalAmount); // 默认总价等于原价

        // 应用优惠券
        if (orderRequest.getCouponCode() != null && !orderRequest.getCouponCode().isEmpty()) {
            applyCoupon(newOrder, orderRequest.getCouponCode());
        }

        orderMapper.insertOrder(newOrder);

        for (OrderItem item : requestedItems) {
            item.setOrderId(newOrder.getId());
        }
        orderMapper.insertOrderItems(requestedItems);
        newOrder.setItems(requestedItems);
        return newOrder;
    }

    private void applyCoupon(Order order, String couponCode) {
        // 1. 检查用户是否拥有此优惠券且该券是否可用
        CustomerCoupon customerCoupon = couponMapper.findCustomerCoupon(order.getCustomerId(), couponCode);
        if (customerCoupon == null || customerCoupon.getIsUsed()) {
            throw new IllegalStateException("Coupon is not valid, not found, or already used.");
        }

        // 2. 获取优惠券的详细规则
        Coupon coupon = couponMapper.findByCode(couponCode);
        if (coupon == null || (coupon.getValidTo() != null && coupon.getValidTo().isBefore(LocalDateTime.now()))) {
            throw new IllegalStateException("Coupon does not exist or has expired.");
        }

        // 3. 检查是否满足最低消费门槛
        if (order.getOriginalAmount().compareTo(coupon.getMinSpend()) < 0) {
            throw new IllegalStateException("Order amount does not meet the minimum spend of the coupon: " + coupon.getMinSpend());
        }

        // 4. 根据优惠券类型计算折扣金额
        BigDecimal discountAmount = BigDecimal.ZERO;
        if (DiscountType.FIXED.name().equalsIgnoreCase(coupon.getDiscountType())) {
            discountAmount = coupon.getDiscountValue();
        } else if (DiscountType.PERCENTAGE.name().equalsIgnoreCase(coupon.getDiscountType())) {
            discountAmount = order.getOriginalAmount().multiply(coupon.getDiscountValue()).setScale(2, RoundingMode.HALF_UP);
        }

        BigDecimal finalAmount = order.getOriginalAmount().subtract(discountAmount);
        // 确保优惠后金额不小于0
        if (finalAmount.compareTo(BigDecimal.ZERO) < 0) {
            finalAmount = BigDecimal.ZERO;
            discountAmount = order.getOriginalAmount(); // 折扣金额不能超过原价
        }

        order.setDiscountAmount(discountAmount);
        order.setTotalAmount(finalAmount);
        order.setCouponCode(couponCode);

        // 5. 更新优惠券为已使用状态
        customerCoupon.setIsUsed(true);
        couponMapper.useCustomerCoupon(customerCoupon);
    }
}