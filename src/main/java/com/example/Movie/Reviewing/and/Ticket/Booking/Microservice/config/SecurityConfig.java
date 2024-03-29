package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.config;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Authority;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(myUserDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/signup/customer/**").permitAll()
                .antMatchers("/signup/admin/**").permitAll()
                .antMatchers("/admin/**").hasAuthority(Authority.ADMIN.toString())
                .antMatchers("/movie/**").hasAnyAuthority(Authority.CUSTOMER.toString())
                .antMatchers("/review/**").hasAnyAuthority(Authority.CUSTOMER.toString())
                .antMatchers("/show/**").hasAnyAuthority(Authority.CUSTOMER.toString())
                .antMatchers("/theater/**").hasAnyAuthority(Authority.CUSTOMER.toString())
                .antMatchers("/user/**").hasAnyAuthority(Authority.ADMIN.toString(),Authority.CUSTOMER.toString())
                .antMatchers("/ticket/**").hasAuthority(Authority.CUSTOMER.toString())
                .and().httpBasic();
    }

}
