package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.controller;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.IdNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.NoMatchFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.User;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.BookingCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;


    // localhost:8080/ticket/book
    @PostMapping("/book")
    public ResponseEntity bookTicket(@RequestBody @Valid BookingCreateRequest bookingCreateRequest) throws IdNotFoundException, NoMatchFoundException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User myUser = (User) authentication.getPrincipal();

        if(myUser.getCustomer() == null){
            throw new NoMatchFoundException("Invalid User");
        }

        return new ResponseEntity(ticketService.bookTicket(bookingCreateRequest,myUser.getCustomer()), HttpStatus.OK);

    }

    // localhost:8080/ticket/id
    @GetMapping("/id")
    public ResponseEntity getTicket(@RequestParam("id") @Min(value = 1,message = "Ticket Id cannot be -ve") long id){
        return new ResponseEntity(ticketService.getTicket(id),HttpStatus.FOUND);
    }

    // localhost:8080/ticket/all
    @GetMapping("/all")
    public ResponseEntity getTicket() throws NoMatchFoundException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User myUser = (User) authentication.getPrincipal();

        if(myUser.getCustomer() == null){
            throw new NoMatchFoundException("Invalid User");
        }

        long studentId = myUser.getCustomer().getId();
        return new ResponseEntity(ticketService.getTickets(studentId),HttpStatus.FOUND);

    }

}
