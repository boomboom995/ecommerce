package com.customer.ecommerce.service;
import com.customer.ecommerce.model.Customer;
import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers(String phone);
    Customer getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
}