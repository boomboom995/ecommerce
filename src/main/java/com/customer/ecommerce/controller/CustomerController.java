package com.customer.ecommerce.controller;
import com.customer.ecommerce.common.R;
import com.customer.ecommerce.model.Customer;
import com.customer.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public R<List<Customer>> getAllCustomers(@RequestParam(required = false) String phone) {
        return R.success(customerService.getAllCustomers(phone));
    }

    @PostMapping
    public R<Customer> createCustomer(@RequestBody Customer customer) {
        return R.success(customerService.createCustomer(customer));
    }
}