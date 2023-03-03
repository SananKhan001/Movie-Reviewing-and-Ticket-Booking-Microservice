package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.IdNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.SeatType;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Theater;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.TheaterSeats;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.TheaterRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.TheaterSeatsRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.TheaterCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.TheaterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

    private static Logger LOGGER = LoggerFactory.getLogger(TheaterService.class);

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatsRepository theaterSeatsRepository;

    public TheaterResponse addTheater(TheaterCreateRequest theaterCreateRequest) {

        Theater theater = theaterCreateRequest.to();

        getTheaterSeats(theater);

        theater = theaterRepository.save(theater);

        LOGGER.info("Added new user --> {}",theater);

        return theater.to();

    }

    private void getTheaterSeats(Theater theater) {

        List<TheaterSeats> seats = new ArrayList<>();

        seats.add(getTheaterSeat("1A", SeatType.REGULAR, theater));
        seats.add(getTheaterSeat("1B", SeatType.REGULAR, theater));
        seats.add(getTheaterSeat("1C", SeatType.REGULAR, theater));
        seats.add(getTheaterSeat("1D", SeatType.REGULAR, theater));
        seats.add(getTheaterSeat("1E", SeatType.REGULAR, theater));

        seats.add(getTheaterSeat("2A", SeatType.RECLINER, theater));
        seats.add(getTheaterSeat("2B", SeatType.RECLINER, theater));
        seats.add(getTheaterSeat("2C", SeatType.RECLINER, theater));
        seats.add(getTheaterSeat("2D", SeatType.RECLINER, theater));
        seats.add(getTheaterSeat("2E", SeatType.RECLINER, theater));

        theaterSeatsRepository.saveAll(seats);

    }

    private TheaterSeats getTheaterSeat(String seatNumber, SeatType seatType, Theater theater) {

        return TheaterSeats.builder()
                .theater(theater)
                .seatType(seatType)
                .seatNumber(seatNumber)
                .build();

    }


    public TheaterResponse getTheater(long id) throws IdNotFoundException {

        Optional<Theater> optionalTheater = theaterRepository.findById(id);

        if(optionalTheater.isEmpty()){
            throw new IdNotFoundException("Given id is not present");
        }

        return optionalTheater.get().to();
    }
}
