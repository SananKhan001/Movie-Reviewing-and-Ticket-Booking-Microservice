package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show,Long> {
    @Query(value = "select * from shows s, movie_table m, theaters t where m.id=s.movie_id and s.theater_id=t.id and m.title=? and t.city=?",nativeQuery = true)
    List<Show> findByMovieNameAndCity(String movieName, String cityName);

    @Query(value = "select * from shows s,theaters t where s.theater_id = t.id and t.city = ?2 and t.name = ?1",nativeQuery = true)
    List<Show> findByTheaterAndCity(String theaterName, String cityName);

    @Query(value = "select * from shows s, theaters t where s.theater_id = t.id and t.city = ?1",nativeQuery = true)
    List<Show> findByCity(String cityName);
}
