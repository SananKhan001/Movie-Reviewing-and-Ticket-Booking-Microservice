package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Admin;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreateRequest {

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

    public Admin to(){

        return Admin.builder()
                .name(this.name)
                .mobile(this.mobile)
                .email(this.email)
                .build();

    }

    public UserCreateRequest toUser(){

        return UserCreateRequest.builder()
                .admin(to())
                .password(this.password)
                .username(this.username)
                .build();

    }
}
