package com.customer.ecommerce.service;

import com.customer.ecommerce.common.exception.ResourceNotFoundException;
import com.customer.ecommerce.dao.*;
import com.customer.ecommerce.model.*;
import com.customer.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;
    private final ProductVariantMapper variantMapper;
    private final CouponMapper couponMapper;

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

            originalAmount = originalAmount.add(variant.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));
            variant.setStock(variant.getStock() - itemDto.getQuantity());
            variantMapper.updateStock(variant);
        }

        newOrder.setOriginalAmount(originalAmount);
        newOrder.setTotalAmount(originalAmount);

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
        if(customerCoupon == null || customerCoupon.getIsUsed()){
             throw new IllegalStateException("Coupon not valid or already used.");
        }
        
        // 假设优惠券为固定减10元
        BigDecimal discountAmount = new BigDecimal("10.00");
        order.setDiscountAmount(discountAmount);
        order.setTotalAmount(order.getOriginalAmount().subtract(discountAmount));
        order.setCouponCode(couponCode);
        
        customerCoupon.setIsUsed(true);
        couponMapper.useCustomerCoupon(customerCoupon);
    }
}