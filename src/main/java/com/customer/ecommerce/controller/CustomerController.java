package com.customer.ecommerce.controller;
import com.customer.ecommerce.common.R;
import com.customer.ecommerce.model.Coupon;
import com.customer.ecommerce.model.Customer;
import com.customer.ecommerce.model.dto.AssignCouponRequest;
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
    // ▼▼▼ 新增 API 端点 ▼▼▼
    @PostMapping("/{customerId}/coupons")
    public R<String> assignCoupon(
            @PathVariable Long customerId,
            @RequestBody AssignCouponRequest request) {

        couponService.assignCouponToCustomer(customerId, request.getCouponId());
        return R.success("优惠券发放成功");
    }
    @PutMapping("/{id}")
    public R<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return R.success(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/{id}")
    public R<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return R.success("客户删除成功");
    }
}