package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TicketResponse {

    private long id;

    private String allottedSeats;

    private double amount;

    private Date bookedAt;

    private String movieName;

    private LocalDateTime showTime;

    private String theaterName;

    private String address;

}
