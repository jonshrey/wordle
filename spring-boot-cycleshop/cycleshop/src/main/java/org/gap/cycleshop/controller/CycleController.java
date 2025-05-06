package org.gap.cycleshop.controller;

import org.gap.cycleshop.model.Cycle;
import org.gap.cycleshop.repository.CycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cycles")
public class CycleController {
    @Autowired
	private CycleRepository cycleRepository;

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
}
