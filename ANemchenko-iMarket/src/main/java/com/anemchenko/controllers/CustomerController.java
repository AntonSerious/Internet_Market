package com.anemchenko.controllers;

import com.anemchenko.services.CustomerService;
import com.anemchenko.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public String showCustomersPage(Model model){
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers_form";
    }
    @GetMapping("/customers/{id}")
    public String showProductInfo(Model model, @PathVariable Long id){
        model.addAttribute("customerDao", customerService.getCustomerById(id));
        model.addAttribute("buyDetails", customerService.findCustomerDetailsById(id));
        return "customer_details_form";
    }
}
