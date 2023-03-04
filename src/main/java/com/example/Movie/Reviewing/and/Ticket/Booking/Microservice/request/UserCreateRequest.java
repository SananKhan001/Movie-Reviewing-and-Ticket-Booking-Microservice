package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request;


import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Admin;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Authority;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Customer;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    private String username;
    private String password;
    private Authority authority;
    private Admin admin;
    private Customer customer;

}
