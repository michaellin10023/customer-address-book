package com.example.mycrudapp.service;

import com.example.mycrudapp.dao.CustomerDAO;
import com.example.mycrudapp.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements  CustomerService{

    private CustomerDAO customerDAO;

    @Autowired
    public CustomerServiceImpl(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    @Override
    @Transactional
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Override
    @Transactional
    public Customer findById(int id) {
        return customerDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        customerDAO.save(customer);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        customerDAO.deleteById(id);
    }

    @Override
    @Transactional
    public List<Customer> findByKeyword(String keyword) { return customerDAO.findByKeyword(keyword); }

}
