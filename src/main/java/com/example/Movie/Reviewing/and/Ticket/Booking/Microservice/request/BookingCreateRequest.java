package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.SeatType;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingCreateRequest {

    @NotEmpty(message = "SeatNumbers cannot be empty")
    private HashSet<String> seatsNumbers;

    @Min(value = 1,message = "Invalid show ID")
    private long showId;

    @NotNull(message = "seatType cannot be null")
    private SeatType seatType;

}
