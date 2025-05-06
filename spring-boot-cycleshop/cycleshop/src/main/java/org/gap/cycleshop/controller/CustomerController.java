package org.gap.cycleshop.controller;

import org.gap.cycleshop.model.Customer;
import org.gap.cycleshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @PutMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }
}
