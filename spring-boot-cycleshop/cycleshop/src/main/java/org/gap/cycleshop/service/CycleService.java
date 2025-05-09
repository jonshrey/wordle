package org.gap.cycleshop.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.gap.cycleshop.entity.Customer;
import org.gap.cycleshop.entity.CustomerCycle;
import org.gap.cycleshop.entity.Cycle;
import org.gap.cycleshop.repository.CustomerCycleRepository;
import org.gap.cycleshop.repository.CustomerRepository;
import org.gap.cycleshop.repository.CycleRepository;
import org.gap.cycleshop.service.exception.CycleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CycleService {
    @Autowired
    private CycleRepository cycleRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerCycleRepository customerCycleRepository;

    public CycleServiceResponse borrowCycle(Integer cycleId, Integer customerId) throws CycleException{
        Cycle cycle = cycleRepository.findById(cycleId)
                            .orElseThrow(() -> new CycleException("Cycle not found with ID " + cycleId));
        Customer customer = customerRepository.findById(customerId)
                            .orElseThrow(() -> new CycleException("Customer not found " + customerId));
        
        if (cycle.getStock() == 0) {
            throw new CycleException("No stock left for the chosen cycle with ID " + cycleId);
        }

        cycle.setStock(cycle.getStock() - 1);

        var borrowedCycle = new CustomerCycle();
        borrowedCycle.setUser(customer);
        borrowedCycle.setCycle(cycle);

        customerCycleRepository.save(borrowedCycle);
        cycleRepository.save(cycle);
        return new CycleServiceResponse("Successfully borrowed cycle at " + borrowedCycle.getBorrowedAt() + "with bookingid " + borrowedCycle.getBookingId(), cycle.getStock());
    }

    public CycleServiceResponse returnCycle(Integer cycleId, Integer customerId) throws CycleException {
        Cycle cycle = cycleRepository.findById(cycleId)
                            .orElseThrow(() -> new CycleException("Cycle not found with ID " + cycleId));
        Customer customer = customerRepository.findById(customerId)
                            .orElseThrow(() -> new CycleException("Customer not found " + customerId));
        
        List<CustomerCycle> allBorroweds = customerCycleRepository.findAllByUserAndCycleOrderByBorrowedAtAsc(customer, cycle);
        if (allBorroweds.size() == 0) {
            throw new CycleException("You have not borrowed any cycles!");
        }
        var oldestBorrowed = allBorroweds.get(0);
        customerCycleRepository.delete(oldestBorrowed);
        cycle.setStock(cycle.getStock() + 1);
        cycleRepository.save(cycle);

        return new CycleServiceResponse("Successfully returned cycle ", cycle.getStock());
    }
}
