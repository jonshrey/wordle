package org.gap.cycleshop.controller;

import org.gap.cycleshop.repository.CycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cycles")
public class CycleHTMLController {
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
        session.setAttribute("loggedinuser", username);
        
        return "redirect:/cycles/all";
        // var allcycles = cycleRepository.findAll();
        // model.addAttribute("cycles", allcycles);
        // model.addAttribute("user", session.getAttribute("loggedinuser"));
        // return "allcycles";
    }
}
