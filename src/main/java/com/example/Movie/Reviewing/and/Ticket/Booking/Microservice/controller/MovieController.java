package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.controller;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.GenreNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.IdNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.NoMatchFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.TitleNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.MovieResponse;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    // localhost:8080/movie/title
    @GetMapping("/title")
    public MovieResponse findMovie(@RequestParam("title") String title) throws TitleNotFoundException {

        return movieService.findMovie(title);

    }

    // localhost:8080/movie/genre
    @GetMapping("/genre")
    public List<MovieResponse> findMovieByGenre(@RequestParam("genre") String genre,@RequestParam("top") int top) throws GenreNotFoundException, NoMatchFoundException {

        return movieService.findMovieByGenre(genre,top);

    }

    // localhost:8080/movie/id
    @GetMapping("/id")
    public ResponseEntity getMovieById(@RequestParam("id") long id) throws IdNotFoundException {
        return new ResponseEntity<>(movieService.getMovie(id), HttpStatus.OK);
    }

}
