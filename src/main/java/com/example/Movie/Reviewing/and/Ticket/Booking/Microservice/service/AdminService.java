package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Movie;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.MovieCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    MovieService movieService;

    public MovieResponse addMovie(MovieCreateRequest movieCreateRequest) {

        return movieService.addMovie(movieCreateRequest);

    }

}
