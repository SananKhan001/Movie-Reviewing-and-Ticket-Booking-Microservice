package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.controller;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.AdminCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.CustomerCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.AdminService;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.CustomerService;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    // localhost:8080/signup/customer
    @PostMapping("/customer")
    public ResponseEntity addUser(@RequestBody @Valid CustomerCreateRequest customerCreateRequest){
        return new ResponseEntity(customerService.create(customerCreateRequest), HttpStatus.OK);
    }

    // localhost:8080/signup/admin
    @PostMapping("/admin")
    public ResponseEntity addUser(@RequestBody @Valid AdminCreateRequest adminCreateRequest){
        return new ResponseEntity(adminService.create(adminCreateRequest), HttpStatus.OK);
    }

}
