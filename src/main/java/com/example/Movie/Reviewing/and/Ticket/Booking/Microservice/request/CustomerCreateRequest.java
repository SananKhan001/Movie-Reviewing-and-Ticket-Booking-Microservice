package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Customer;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String mobile;

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Customer to(){

        return Customer.builder()
                .email(this.email)
                .mobile(this.mobile)
                .name(this.name)
                .build();

    }

    public UserCreateRequest toUser(){

        return UserCreateRequest.builder()
                .customer(to())
                .password(this.password)
                .username(this.username)
                .build();

    }

}
