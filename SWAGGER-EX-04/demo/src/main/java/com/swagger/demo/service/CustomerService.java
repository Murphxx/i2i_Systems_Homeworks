package com.swagger.demo.service;

import com.swagger.demo.model.Customer;
import com.swagger.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    public Customer getById(Long id) {
        return repository.findById(id);
    }

    public List<Customer> getAll() {
        return repository.findAll();
    }

    public Customer update(Long id, Customer customer) {
        if (repository.existsById(id)) {
            customer.setId(id);
            return repository.save(customer);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
