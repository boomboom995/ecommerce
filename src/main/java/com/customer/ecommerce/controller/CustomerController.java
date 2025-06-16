package com.customer.ecommerce.controller;
import com.customer.ecommerce.common.R;
import com.customer.ecommerce.model.Coupon;
import com.customer.ecommerce.model.Customer;
import com.customer.ecommerce.service.CouponService;
import com.customer.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CouponService couponService; // 注入CouponService

    @GetMapping
    public R<List<Customer>> getAllCustomers(@RequestParam(required = false) String phone) {
        return R.success(customerService.getAllCustomers(phone));
    }

    @PostMapping
    public R<Customer> createCustomer(@Valid @RequestBody Customer customer) { // 添加 @Valid 注解
        return R.success(customerService.createCustomer(customer));
    }

    // 新增：查询指定客户所有可用的优惠券
    @GetMapping("/{customerId}/coupons")
    public R<List<Coupon>> getAvailableCoupons(@PathVariable Long customerId) {
        return R.success(couponService.getAvailableCouponsByCustomerId(customerId));
    }
}