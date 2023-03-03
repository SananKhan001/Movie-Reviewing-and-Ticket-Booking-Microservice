package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.IdNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.*;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.MovieRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.ShowRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.ShowSeatRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.TheaterRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.ShowCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.ShowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowService {
    @Autowired
    private  MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private ShowRepository showRepository;

    public ShowService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public ShowResponse addShow(ShowCreateRequest showCreateRequest) throws IdNotFoundException {

        Optional<Movie> optionalMovie = movieRepository.findById(showCreateRequest.getMovieId());
        if(optionalMovie.isEmpty()){
            throw new IdNotFoundException("Given Id is not available");
        }

        Optional<Theater> optionalTheater = theaterRepository.findById(showCreateRequest.getTheaterId());
        if(optionalMovie.isEmpty()){
            throw new IdNotFoundException("Given Id is not available");
        }

        Show show = showCreateRequest.to();

        show.setMovie(optionalMovie.get());
        show.setTheater(optionalTheater.get());

        show = showRepository.save(show);

        generateShowSeats(show.getTheater().getSeats(),show);

        show = showRepository.save(show);

        return show.to();

    }

    private void generateShowSeats(List<TheaterSeats> theaterSeats, Show show) {

        List<ShowSeat> showSeats = theaterSeats.stream()
                .map(x ->
                    ShowSeat.builder()
                            .seatNumber(x.getSeatNumber())
                            .seatType(x.getSeatType())
                            .rate(100)
                            .show(show)
                            .build()
                ).collect(Collectors.toList());

        showSeatRepository.saveAll(showSeats);
    }
}
