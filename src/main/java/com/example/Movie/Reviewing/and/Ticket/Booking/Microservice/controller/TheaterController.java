package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.controller;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.IdNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.TheaterCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    // localhost:8080/theater/add
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid TheaterCreateRequest theaterCreateRequest){
        return new ResponseEntity(theaterService.addTheater(theaterCreateRequest), HttpStatus.OK);
    }

    // localhost:8080/theater/id
    @GetMapping("/id")
    public ResponseEntity getUser(@RequestParam("id") @Min(value = 1, message = "Theater Id cannot be -ve") long id) throws IdNotFoundException {
        return new ResponseEntity(theaterService.getTheater(id),HttpStatus.OK);
    }

}
