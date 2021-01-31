package com.example.mycrudapp.service;

import com.example.mycrudapp.entity.Customer;
import java.util.List;

public interface CustomerService {

    public List<Customer> findAll();

    public Customer findById(int id);

    public void save(Customer customer);

    public void deleteById(int id);

    public List<Customer> findByKeyword(String keyword);
}
