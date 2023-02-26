package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.controller;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.IdNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.RatingLimitExceededException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Review;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.ReviewCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.ReviewResponse;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    // localhost:8080/review/add
    @PostMapping("/add")
    public void addReview(@RequestBody @Valid ReviewCreateRequest reviewCreateRequest) throws IdNotFoundException, RatingLimitExceededException {
        reviewService.addReview(reviewCreateRequest);
    }

    // localhost:8080/review/find
    @GetMapping("/find")
    public ReviewResponse getReview(@RequestParam("id") Long reviewId) throws IdNotFoundException {
        return reviewService.getReview(reviewId);
    }

}
