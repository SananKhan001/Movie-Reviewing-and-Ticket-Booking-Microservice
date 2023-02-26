package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Movie;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.MovieRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.MovieCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    public MovieResponse addMovie(MovieCreateRequest movieCreateRequest) {

        Movie movie = movieCreateRequest.to();

        movie = movieRepository.save(movie);

        return movie.to();

    }
}
