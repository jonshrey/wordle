package org.gap.cycleshop.controller;

import org.gap.cycleshop.model.Cycle;
import org.gap.cycleshop.model.CustomerCycle;
import org.gap.cycleshop.repository.CycleRepository;
import org.gap.cycleshop.repository.CustomerCycleRepository;
import org.gap.cycleshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cyclesrestapi")
public class CycleRESTController {
    @Autowired
	private CycleRepository cycleRepository;
    @Autowired
    private CustomerRepository userRepository;
    @Autowired
    private CustomerCycleRepository userCycleRepository;

    @GetMapping("/createDefault")
    public String createDefault() {
        var cycle = new Cycle();
        cycle.setModel("Hercules");
        cycle.setColour("red");
        cycle.setStock(100);
        cycle.setRentPerDay(25.0d);

        cycleRepository.save(cycle);
        return "Successfully created a cycle with ID: " + cycle.getId();
    }

    @GetMapping("/all")
    public Iterable<Cycle> cycleList() {
        return cycleRepository.findAll();
    }

    @PutMapping("/create")
    public ResponseEntity<Cycle> create(@RequestBody Cycle cycle) {
        cycleRepository.save(cycle);
        return new ResponseEntity<Cycle>(cycle, HttpStatus.CREATED);
    }

    @PutMapping("/borrow")
    public ResponseEntity<BorrowResponse> borrow(@RequestBody BorrowRequest borrowRequest) {
        var availableCycles = cycleRepository.findAllByModelAndStockGreaterThan(borrowRequest.model(), 0);
        var optionalUser = userRepository.findByName(borrowRequest.username());

        if (availableCycles.size() == 0) {
            return new ResponseEntity<BorrowResponse>(new BorrowResponse("No cycles remaining of this model", 0), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<BorrowResponse>( new BorrowResponse("User not found!", 0), HttpStatus.NOT_FOUND);
        }

        var selectedCycle = availableCycles.get(0);

        var userCycle = new CustomerCycle();
        userCycle.setUser(optionalUser.get());
        userCycle.setCycle(selectedCycle);

        selectedCycle.setStock(selectedCycle.getStock() - 1);

        userCycleRepository.save(userCycle);
        cycleRepository.save(selectedCycle);
        return new ResponseEntity<BorrowResponse>(
            new BorrowResponse("Successfully borrowed cycle at " + userCycle.getBorrowedAt() + "with bookingid " + userCycle.getBookingId(), selectedCycle.getStock())
            , HttpStatus.CREATED);
    }

    @PutMapping("/return")
    public ResponseEntity<ReturnResponse> returnCycle (@RequestBody ReturnRequest request) {
        return null;
    }
}
