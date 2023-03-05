package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketCreateRequest {

    @NotBlank
    private String allottedSeats;

    @NotBlank
    private double amount;

}
