package org.gap.cycleshop.repository;

import java.util.List;
import java.util.Optional;

import org.gap.cycleshop.entity.Customer;
import org.gap.cycleshop.entity.CustomerCycle;
import org.gap.cycleshop.entity.Cycle;
import org.springframework.data.repository.CrudRepository;

public interface CustomerCycleRepository extends CrudRepository<CustomerCycle, Integer> {
    public Optional<CustomerCycle> findByUserAndCycle(Customer user, Cycle cycle); //must return 1 and exactly 1 rewsult
    public List<CustomerCycle> findAllByUserAndCycleOrderByBorrowedAtAsc(Customer user, Cycle cycle);
}
