package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.IdNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.*;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.MovieRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.ShowRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.ShowSeatRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.TheaterRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.ShowCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.ShowResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private static Logger LOGGER = LoggerFactory.getLogger(ShowService.class);
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
        if(optionalTheater.isEmpty()){
            throw new IdNotFoundException("Given Id is not available");
        }

        Show show = showCreateRequest.to();

        show.setMovie(optionalMovie.get());
        show.setTheater(optionalTheater.get());

        show = showRepository.save(show);

        //show.setSeats(generateShowSeats(show.getTheater().getSeats(),show));

        generateShowSeats(show.getTheater().getSeats(), show);

        //show = showRepository.save(show);

        //LOGGER.info("No. of seats show have --> {}",show.getSeats().size());

        return show.to();

    }

    private List<ShowSeat> generateShowSeats(List<TheaterSeats> theaterSeats, Show show) {

        List<ShowSeat> showSeats = theaterSeats.stream()
                .map(x ->
                    ShowSeat.builder()
                            .seatNumber(x.getSeatNumber())
                            .seatType(x.getSeatType())
                            .show(show)
                            .rate(100)
                            .build()
                ).collect(Collectors.toList());

        return showSeatRepository.saveAll(showSeats);
    }

    public List<ShowResponse> searchShows(String movieName, String cityName, String theaterName) {

        if(!StringUtils.hasText(cityName)){
            return new ArrayList<>();
        }

        List<Show> shows = new ArrayList<>();
        if(StringUtils.hasText(movieName)){
            shows = showRepository.findByMovieNameAndCity(movieName,cityName);
        }

        return shows.stream().map(x -> x.to()).collect(Collectors.toList());
    }
}
