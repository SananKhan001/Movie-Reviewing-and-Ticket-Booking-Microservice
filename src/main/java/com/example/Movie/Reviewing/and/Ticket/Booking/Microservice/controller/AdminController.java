package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.controller;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.IdNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Show;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.ShowSeat;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.MovieRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.ShowRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.*;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.MovieResponse;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.ShowResponse;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private TheaterService theaterService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ShowService showService;

    @Autowired
    private TicketService ticketService;

    // localhost:8080/admin/movie/add
    @PostMapping("/movie/add")
    public ResponseEntity<MovieResponse> addMovie(@RequestBody @Valid MovieCreateRequest movieCreateRequest){
        return new ResponseEntity<>(adminService.addMovie(movieCreateRequest), HttpStatus.CREATED);
    }

    // localhost:8080/admin/show/add
    @PostMapping("/show/add")
    public ResponseEntity addShow(@RequestBody @Valid ShowCreateRequest showCreateRequest) throws IdNotFoundException {
        return new ResponseEntity(showService.addShow(showCreateRequest), HttpStatus.OK);
    }

    // localhost:8080/admin/theater/add
    @PostMapping("/theater/add")
    public ResponseEntity addUser(@RequestBody @Valid TheaterCreateRequest theaterCreateRequest){
        return new ResponseEntity(theaterService.addTheater(theaterCreateRequest), HttpStatus.OK);
    }

    // localhost:8080/admin/ticketId
    @GetMapping("/ticketId")
    public ResponseEntity getTicket(@RequestParam("id") @Min(value = 1,message = "Ticket Id cannot be -ve") long id){
        return new ResponseEntity(ticketService.getTicket(id),HttpStatus.FOUND);
    }

}
