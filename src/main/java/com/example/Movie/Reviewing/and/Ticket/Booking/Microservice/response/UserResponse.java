package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Authority;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String name;
    private String mobile;
    private String email;
    private String username;
    private Authority authority;

}
