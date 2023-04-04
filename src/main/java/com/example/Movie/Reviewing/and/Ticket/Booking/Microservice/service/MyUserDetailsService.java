package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Authority;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.User;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.MyUserRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.UserCacheRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.AdminCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.CustomerCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.UserCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserCacheRepository userCacheRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User myUser = null;

        try {
            myUser = userCacheRepository.get(username);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        if(myUser != null){
            LOGGER.info("Got User from Cache !!!");
            return myUser;
        }

        myUser = myUserRepository.findByName(username);

        if(myUser == null){
            throw new UsernameNotFoundException("Invalid Credentials");
        }

        LOGGER.info("Got User from Repository !!!");

        try {
            userCacheRepository.set(myUser);
            LOGGER.info("user saved at cache !!!");
        }
        catch (Exception ex){
            ex.printStackTrace();
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

        return myUserRepository.save(myUserBuilder.build());
    }

}
