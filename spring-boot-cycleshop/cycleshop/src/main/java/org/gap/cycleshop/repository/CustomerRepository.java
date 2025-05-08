package org.gap.cycleshop.repository;

import java.util.Optional;

import org.gap.cycleshop.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    public Optional<Customer> findByName(String name);
    
}
