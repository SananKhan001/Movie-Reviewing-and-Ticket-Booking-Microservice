package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Admin;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Customer;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Movie;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.User;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.AdminRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.AdminCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.CustomerCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.MovieCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.UserCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.MovieResponse;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    MovieService movieService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    AdminRepository adminRepository;

    public MovieResponse addMovie(MovieCreateRequest movieCreateRequest) {

        return movieService.addMovie(movieCreateRequest);

    }

    public UserResponse create(AdminCreateRequest adminCreateRequest) {

        UserCreateRequest userCreateRequest = adminCreateRequest.toUser();

        User myUser = myUserDetailsService.create(userCreateRequest);

        Admin admin = adminCreateRequest.to();

        admin.setMyUser(myUser);

        adminRepository.save(admin);

        return myUser.to(admin);

    }
}
