package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Genre;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Movie;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.MovieResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    public Movie findByTitle(String title);

    public List<Movie> findByGenre(Genre genre);

}
