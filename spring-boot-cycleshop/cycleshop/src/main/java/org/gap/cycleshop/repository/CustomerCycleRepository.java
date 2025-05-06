package org.gap.cycleshop.repository;

import org.gap.cycleshop.model.CustomerCycle;
import org.springframework.data.repository.CrudRepository;

public interface CustomerCycleRepository extends CrudRepository<CustomerCycle, Integer> {
    
}
