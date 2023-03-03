package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.SeatType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ShowSeatsResponse {

    private long id;

    private String seatNumber;

    private int rate;

    private SeatType seatType;

    private boolean booked;

    private Date bookedAt;

}
