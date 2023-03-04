package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Customer;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.User;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.CustomerRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.CustomerCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.UserCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.UserResponse;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.userdetails.UserDetailsResourceFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private CustomerRepository customerRepository;

    public UserResponse create(CustomerCreateRequest customerCreateRequest) {

        UserCreateRequest userCreateRequest = customerCreateRequest.toUser();

        User myUser = myUserDetailsService.create(userCreateRequest);

        Customer customer = customerCreateRequest.to();

        customer.setMyUser(myUser);

        customerRepository.save(customer);

        return myUser.to(customer);
    }
}
