package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.controller;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Show;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.MovieRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.ShowRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.MovieCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.MovieResponse;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.ShowResponse;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    ShowRepository showRepository;

    // localhost:8080/admin/movie/add
    @PostMapping("/movie/add")
    public ResponseEntity<MovieResponse> addMovie(@RequestBody @Valid MovieCreateRequest movieCreateRequest){
        return new ResponseEntity<>(adminService.addMovie(movieCreateRequest), HttpStatus.CREATED);
    }

    // localhost:8080/admin/movie/shows
    @GetMapping("/movie/shows")
    public List<ShowResponse> getShow(@RequestParam("id") long id){
        return movieRepository.findById(id).get().getShows().stream().map(x -> x.to()).collect(Collectors.toList());
    }

    // localhost:8080/admin/show/id
    @GetMapping("/show/id")
    public ShowResponse getShowById(@RequestParam("id") long id){
        return showRepository.findById(id).get().to();
    }
}
