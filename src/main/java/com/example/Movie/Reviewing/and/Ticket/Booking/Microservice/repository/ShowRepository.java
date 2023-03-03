package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Long> {
}
