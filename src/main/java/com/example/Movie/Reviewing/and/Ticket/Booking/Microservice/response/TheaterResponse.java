package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class TheaterResponse {

    private long id;

    private String name;

    private String city;

    private String address;

    private List<ShowResponse> shows;

}
