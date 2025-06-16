package com.customer.ecommerce.service;

import com.customer.ecommerce.dao.*;
import com.customer.ecommerce.dto.OrderRequestDto;
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
    public Order createOrder(OrderRequestDto orderRequestDto) {
        Customer customer = customerMapper.findById(orderRequestDto.getCustomerId());
        if (customer == null) {
            throw new RuntimeException("Customer not found with id: " + orderRequestDto.getCustomerId());
        }

        Order order = new Order();
        order.setCustomerId(customer.getId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal originalAmount = BigDecimal.ZERO;

        for (var itemDto : orderRequestDto.getItems()) {
            ProductVariant variant = variantMapper.findById(itemDto.getVariantId());
            if (variant == null) {
                throw new RuntimeException("Product variant not found: " + itemDto.getVariantId());
            }
            if (variant.getStock() < itemDto.getQuantity()) {
                throw new RuntimeException("Stock not enough for product variant: " + variant.getId());
            }

            originalAmount = originalAmount.add(variant.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));

            OrderItem item = new OrderItem();
            item.setVariantId(variant.getId());
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(variant.getPrice());
            orderItems.add(item);

            variant.setStock(variant.getStock() - itemDto.getQuantity());
            variantMapper.updateStock(variant);
        }

        order.setOriginalAmount(originalAmount);
        order.setTotalAmount(originalAmount);

        if (orderRequestDto.getCouponCode() != null && !orderRequestDto.getCouponCode().isEmpty()) {
            applyCoupon(order, orderRequestDto.getCouponCode());
        }

        orderMapper.insertOrder(order);

        for (OrderItem item : orderItems) {
            item.setOrderId(order.getId());
        }
        orderMapper.insertOrderItems(orderItems);

        order.setItems(orderItems);
        return order;
    }

    private void applyCoupon(Order order, String couponCode) {
        CustomerCoupon customerCoupon = couponMapper.findCustomerCoupon(order.getCustomerId(), couponCode);
        if(customerCoupon == null || customerCoupon.getIsUsed()){
             throw new RuntimeException("Coupon not valid or already used.");
        }
        // ... (此处省略其他优惠券逻辑，如有效期、最低消费等)

        // 假设优惠券为固定减10元
        BigDecimal discountAmount = new BigDecimal("10.00");
        order.setDiscountAmount(discountAmount);
        order.setTotalAmount(order.getOriginalAmount().subtract(discountAmount));
        order.setCouponCode(couponCode);
        
        customerCoupon.setIsUsed(true);
        couponMapper.useCustomerCoupon(customerCoupon);
    }
}