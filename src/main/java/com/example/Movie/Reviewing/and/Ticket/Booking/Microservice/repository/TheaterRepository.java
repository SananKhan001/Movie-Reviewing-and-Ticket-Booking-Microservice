package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater,Long> {

}
