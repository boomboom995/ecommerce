package com.customer.ecommerce.service;

import com.customer.ecommerce.common.enums.DiscountType;
import com.customer.ecommerce.common.exception.ResourceNotFoundException;
import com.customer.ecommerce.dao.*;
import com.customer.ecommerce.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;
    private final ProductVariantMapper variantMapper;
    private final CouponMapper couponMapper;
    // 建议：注入CouponMapper以获取优惠券详情
    // private final CouponMapper couponMapper;


    @Override
    @Transactional
    public Order createOrder(Order orderRequest) { // 直接接收 Model
        Customer customer = customerMapper.findById(orderRequest.getCustomerId());
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found with id: " + orderRequest.getCustomerId());
        }

        // 准备一个新的 Order 对象用于持久化
        Order newOrder = new Order();
        newOrder.setCustomerId(customer.getId());
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setStatus("PENDING");

        BigDecimal originalAmount = BigDecimal.ZERO;

        // 从请求的 order 对象中获取 items
        List<OrderItem> requestedItems = orderRequest.getItems();
        if(requestedItems == null || requestedItems.isEmpty()){
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

            // 【修复】为订单项设置购买时的单价
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

        // 插入订单并获取ID
        orderMapper.insertOrder(newOrder);

        // 关联订单项并批量插入
        for (OrderItem item : requestedItems) {
            item.setOrderId(newOrder.getId());
        }
        orderMapper.insertOrderItems(requestedItems);

        newOrder.setItems(requestedItems);
        return newOrder;
    }

    private void applyCoupon(Order order, String couponCode) {
        CustomerCoupon customerCoupon = couponMapper.findCustomerCoupon(order.getCustomerId(), couponCode);

        // 检查优惠券是否有效
        if (customerCoupon == null || customerCoupon.getIsUsed()) {
            throw new IllegalStateException("Coupon not valid or already used.");
        }

        //  【优化建议】
        //  当前实现是固定的10元折扣。
        //  一个更完整的实现应该像下面这样:
        //  1. 根据 customerCoupon.getCouponId() 从数据库查出 Coupon 的详细信息（类型、面值、使用门槛等）。
        //  2. 检查订单金额是否满足 `minSpend`。
        //  3. 根据 `discountType` (FIXED 或 PERCENTAGE) 计算实际的折扣金额。

        // 假设优惠券为固定减10元
        BigDecimal discountAmount = new BigDecimal("10.00");
        BigDecimal finalAmount = order.getOriginalAmount().subtract(discountAmount);

        // 确保优惠后金额不小于0
        if (finalAmount.compareTo(BigDecimal.ZERO) < 0) {
            finalAmount = BigDecimal.ZERO;
            discountAmount = order.getOriginalAmount(); // 折扣金额不能超过原价
        }

        order.setDiscountAmount(discountAmount);
        order.setTotalAmount(finalAmount);
        order.setCouponCode(couponCode);

        // 更新优惠券为已使用状态
        customerCoupon.setIsUsed(true);
        couponMapper.useCustomerCoupon(customerCoupon);
    }
}