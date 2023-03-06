package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.TicketResponse;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.UserResponse;
import lombok.*;
import org.springframework.jmx.export.annotation.ManagedNotifications;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class NotificationMessage {

    private TicketResponse ticketResponse;
    private UserResponse userResponse;

}
