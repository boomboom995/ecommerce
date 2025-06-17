package com.customer.ecommerce.service;

import com.customer.ecommerce.common.exception.ResourceNotFoundException;
import com.customer.ecommerce.dao.CustomerMapper;
import com.customer.ecommerce.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;

    @Override
    public List<Customer> getAllCustomers(String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            Customer customer = customerMapper.findByPhone(phone);
            // 修正：将单个 customer 对象放入 List 中返回，避免类型转换错误
            return customer != null ? Collections.singletonList(customer) : Collections.emptyList();
        }
        return customerMapper.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        Customer customer = customerMapper.findById(id);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found with id: " + id);
        }
        return customer;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customerMapper.insert(customer);
        return customer;
    }

    // ▼▼▼ 补全缺失的方法实现 ▼▼▼

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        // 先用 getCustomerById 确认客户是否存在，如果不存在会直接抛出异常
        getCustomerById(id);
        customer.setId(id); // 确保ID正确
        customerMapper.update(customer);
        return customer;
    }

    @Override
    public void deleteCustomer(Long id) {
        // 警告：直接删除客户，如果该客户有关联的订单，可能会导致数据库外键约束失败。
        // 在真实项目中，通常采用“软删除”（设置一个is_active标志位）。
        getCustomerById(id); // 先确认客户存在
        customerMapper.deleteById(id);
    }
}