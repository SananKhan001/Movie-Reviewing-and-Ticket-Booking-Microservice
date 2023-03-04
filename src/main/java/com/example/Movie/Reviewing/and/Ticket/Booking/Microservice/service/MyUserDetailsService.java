package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Authority;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.User;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.UserRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.AdminCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.CustomerCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.UserCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User myUser = userRepository.findByName(username);

        if(myUser == null){
            throw new UsernameNotFoundException("Invalid Credentials");
        }

        return myUser;

    }

    public User create(UserCreateRequest userCreateRequest) {

        User.UserBuilder myUserBuilder = User.builder()
                .name(userCreateRequest.getUsername())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()));

        if(userCreateRequest.getCustomer() != null){
            myUserBuilder.authority(Authority.CUSTOMER);
        }
        else myUserBuilder.authority(Authority.ADMIN);

        return userRepository.save(myUserBuilder.build());
    }
}
