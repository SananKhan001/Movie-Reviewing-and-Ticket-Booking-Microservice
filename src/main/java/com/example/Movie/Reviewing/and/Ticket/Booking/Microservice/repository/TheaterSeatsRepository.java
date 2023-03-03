package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.TheaterSeats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterSeatsRepository extends JpaRepository<TheaterSeats,Long> {
}
