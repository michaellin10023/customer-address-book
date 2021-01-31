package com.example.mycrudapp.controller;

import com.example.mycrudapp.entity.Customer;
import com.example.mycrudapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String listCustomers(Model model, @Param("keyword") String keyword){

        List<Customer> customers = customerService.findAll();
        if(keyword != null){
            customers = customerService.findByKeyword(keyword);
        }

        model.addAttribute("customers", customers);

        return "customerlist";
    }

    @GetMapping("/showFormForAdd")
    public String addForm(Model model){

        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customerform";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("customer") Customer theCustomer){

        customerService.save(theCustomer);
        return "redirect:/customers/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id,
                                    Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);

        return "customerform";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("customer") Customer theCustomer){

        customerService.save(theCustomer);
        return "redirect:/customers/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("customerId") int id){
        customerService.deleteById(id);
        return "redirect:/customers/list";
    }
}
