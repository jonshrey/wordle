package org.gap.cycleshop.controller;

import org.gap.cycleshop.repository.CustomerRepository;
import org.gap.cycleshop.repository.CycleRepository;
import org.gap.cycleshop.service.CycleServiceResponse;
import org.gap.cycleshop.service.exception.CustomerException;
import org.gap.cycleshop.service.CustomerService;
import org.gap.cycleshop.service.CycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/cycles")
public class CycleHTMLController {

    //YAGNI
    //You Ain't Gonna Need It

    @Autowired
    private CycleService cycleService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CycleRepository cycleRepository;

    

    @GetMapping("/all")
    public String listAllCycles(Model model, HttpSession session) {
        var allcycles = cycleRepository.findAll();
        model.addAttribute("cycles", allcycles);
        model.addAttribute("user", session.getAttribute("loggedinuser"));
        
        return "allcycles";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Uses the Post-Redirect-Get pattern. No more worrying about resending POSTs.
     * @param username
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, Model model, HttpSession session) {
        var customerFromDb = customerRepository.findByName(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        session.setAttribute("loggedinuser", username);
        session.setAttribute("loggedinuserid", customerFromDb.getId());
        return "redirect:/cycles/all";
    }

    @PostMapping("/borrow/{id}")
    public String borrow(@PathVariable("id") Integer cycleId, HttpSession session) {
        final Integer userId; 
        try {
            userId = customerService.getLoggedInUserId(session);
        } catch(CustomerException e) {
            e.printStackTrace();
            return "redirect:/cycles/login";
        }
        cycleService.borrowCycle(cycleId, userId);
        return "redirect:/cycles/all";
    }

    @PostMapping("/return/{id}")
    public String returnCycle(@PathVariable("id") Integer cycleId, HttpSession session) {
        final Integer userId; 
        try {
            userId = customerService.getLoggedInUserId(session);
        } catch(CustomerException e) {
            e.printStackTrace();
            return "redirect:/cycles/login";
        }
        cycleService.returnCycle(cycleId, userId);
        return "redirect:/cycles/all";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        customerService.logout(session);
        return "redirect:/cycles/login";
    }
}
