package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.controller;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Admin;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Customer;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    // localhost:8080/user/profile
    @GetMapping("/profile")
    public ResponseEntity getUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User myUser = (User) authentication.getPrincipal();

        if(myUser.getAdmin() != null){
            Admin admin = myUser.getAdmin();
            return new ResponseEntity<>(myUser.to(admin),HttpStatus.FOUND);
        }

        Customer customer = myUser.getCustomer();

        return new ResponseEntity(myUser.to(customer),HttpStatus.FOUND);

    }

}
