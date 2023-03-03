package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show,Long> {
    @Query(value = "select * from shows s, movie_table m , theaters t where m.id=s.movie_id and s.theater_id=t.id and m.title=? and city=?",nativeQuery = true)
    List<Show> findByMovieNameAndCity(String movieName, String cityName);
}
