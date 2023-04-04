package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.controller;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Admin;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Customer;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.User;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.MyUserRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MyUserDetailsService myUserUserDetailsService;


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
