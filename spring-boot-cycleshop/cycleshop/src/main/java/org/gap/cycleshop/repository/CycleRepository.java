package org.gap.cycleshop.repository;

import java.util.List;

import org.gap.cycleshop.model.Cycle;
import org.springframework.data.repository.CrudRepository;

public interface CycleRepository extends CrudRepository<Cycle, Integer> {

    public List<Cycle> findAllByModelAndStockGreaterThan(String model, Integer stock);
    
}
