package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.controller;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.IdNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.ShowCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    // localhost:8080/show/search
    @GetMapping("/search")
    public ResponseEntity search(
            @RequestParam(name = "city",required = true) String cityName,
            @RequestParam(name = "movieName",required = false) String movieName,
            @RequestParam(name = "theaterName",required = false) String theaterName){

        return new ResponseEntity(showService.searchShows(movieName,cityName,theaterName),HttpStatus.OK);

    }


}
