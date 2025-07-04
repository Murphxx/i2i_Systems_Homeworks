package com.swagger.demo.repository;

import com.swagger.demo.model.Customer;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {
    private final Map<Long, Customer> database = new HashMap<>();

    public Customer save(Customer customer) {
        database.put(customer.getId(), customer);
        return customer;
    }

    public Customer findById(Long id) {
        return database.get(id);
    }

    public List<Customer> findAll() {
        return new ArrayList<>(database.values());
    }

    public void deleteById(Long id) {
        database.remove(id);
    }

    public boolean existsById(Long id) {
        return database.containsKey(id);
    }
}
