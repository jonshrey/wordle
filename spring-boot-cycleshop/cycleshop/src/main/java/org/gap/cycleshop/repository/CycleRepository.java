package org.gap.cycleshop.repository;

import org.gap.cycleshop.model.Cycle;
import org.springframework.data.repository.CrudRepository;

public interface CycleRepository extends CrudRepository<Cycle, Integer> {
    
}
