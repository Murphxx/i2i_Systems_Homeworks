package com.swagger.demo.controller;

import com.swagger.demo.model.Customer;
import com.swagger.demo.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return service.create(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return service.update(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        service.delete(id);
    }
}
