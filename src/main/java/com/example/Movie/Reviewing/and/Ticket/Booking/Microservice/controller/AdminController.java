package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.controller;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.MovieCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.MovieResponse;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // localhost:8080/admin/movie/add
    @PostMapping("/movie/add")
    public ResponseEntity<MovieResponse> addMovie(@RequestBody @Valid MovieCreateRequest movieCreateRequest){

    }

}
