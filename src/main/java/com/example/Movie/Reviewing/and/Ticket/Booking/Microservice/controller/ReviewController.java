package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.controller;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.ReviewCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.ReviewResponse;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    // localhost:8080/review/add
    @PostMapping("/add")
    public void addReview(@RequestBody @Valid ReviewCreateRequest reviewCreateRequest){

    }

    // localhost:8080/review/add
    @GetMapping("/find")
    public ReviewResponse getReview(@RequestParam("id") Long reviewId){

    }

}
