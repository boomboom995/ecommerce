package com.customer.ecommerce.dao;
import com.customer.ecommerce.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CustomerMapper {
    Customer findById(@Param("id") Long id);
    List<Customer> findAll();
    Customer findByPhone(@Param("phone") String phone);
    int insert(Customer customer);
    int update(Customer customer);
    int deleteById(@Param("id") Long id);
}