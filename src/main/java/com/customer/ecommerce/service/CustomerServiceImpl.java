package com.customer.ecommerce.service;
import com.customer.ecommerce.dao.CustomerMapper;
import com.customer.ecommerce.model.Customer;
import com.customer.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;

    @Override
    public List<Customer> getAllCustomers(String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            return (List<Customer>) customerMapper.findByPhone(phone);
        }
        return customerMapper.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerMapper.findById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customerMapper.insert(customer);
        return customer;
    }
}