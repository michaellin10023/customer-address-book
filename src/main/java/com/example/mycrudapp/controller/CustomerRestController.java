package com.example.mycrudapp.controller;

import com.example.mycrudapp.dao.CustomerDAO;
import com.example.mycrudapp.entity.Customer;
import com.example.mycrudapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    private CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> findAll(){

        return customerService.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable int id){

        Customer customer = customerService.findById(id);
        if(customer == null){
            throw new RuntimeException("customer id not found: " + id);
        }
        return customer;
    }

    @PostMapping("/customers/")
    public Customer addCustomer(@RequestBody Customer customer){

        // just in case pass JSON data with id, set id to 0
        // this is to force a save instead of update

        customer.setId(0);
        customerService.save(customer);
        return customer;
    }

    @PostMapping("/customers/{id}")
    public Customer updateCustomer(@RequestBody Customer customer){

        customerService.save(customer);
        return customer;
    }

    @DeleteMapping("customers/{id}")
    public String deleteCustomer(@PathVariable int id){

        Customer customer = customerService.findById(id);
        if(customer == null){
            throw new RuntimeException("customer id not found: " + id);
        }
        customerService.deleteById(id);
        return "Deleted customer id: " + id;
    }
}
