package org.gap.cycleshop.service;

import org.gap.cycleshop.repository.CustomerRepository;
import org.gap.cycleshop.service.exception.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void login(String username, HttpSession session) {
        var customerFromDb = customerRepository.findByName(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        session.setAttribute("loggedinuser", username);
        session.setAttribute("loggedinuserid", customerFromDb.getId());
    }


    public Integer getLoggedInUserId(HttpSession session) {
        var sessionUserIdObj = session.getAttribute("loggedinuserid");
        if (sessionUserIdObj != null) {
            return (Integer) sessionUserIdObj;
        } else {
            throw new CustomerException("User not found");
        }
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
