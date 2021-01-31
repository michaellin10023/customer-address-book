package com.example.mycrudapp.dao;

import com.example.mycrudapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerDAO  {

    public List<Customer> findAll();

    public Customer findById(int id);

    public void save(Customer customer);

    public void deleteById(int id);

    public List<Customer> findByKeyword(String keyword);
}
