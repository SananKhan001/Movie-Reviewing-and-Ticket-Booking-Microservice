package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.controller;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.IdNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.ShowCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    // localhost:8080/show/add
    @PostMapping("/add")
    public ResponseEntity addShow(@RequestBody ShowCreateRequest showCreateRequest) throws IdNotFoundException {
        return new ResponseEntity(showService.addShow(showCreateRequest), HttpStatus.OK);
    }

}