package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.ShowSeatsResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "show_seats")
public class ShowSeat {

    private static Logger LOGGER = LoggerFactory.getLogger(ShowSeat.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_name",nullable = false)
    private String seatNumber;

    @Column(name = "rate",nullable = false)
    private int rate;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type",nullable = false)
    private SeatType seatType;

    @Column(name = "is_booked",nullable = false)
    private boolean booked;

    @Column(name = "bookedAt")
    @CreationTimestamp
    private Date bookedAt;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("seats")
    private Show show;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("seats")
    private Ticket ticket;

    public static ShowSeatsResponse to(ShowSeat showSeat){

        return ShowSeatsResponse.builder()
                .id(showSeat.id)
                .seatNumber(showSeat.seatNumber)
                .rate(showSeat.rate)
                .seatType(showSeat.seatType)
                .booked(showSeat.booked)
                .bookedAt(showSeat.bookedAt)
                .build();

    }
    public static List<ShowSeatsResponse> to(List<ShowSeat> seats) {

        if(seats == null || seats.isEmpty()){
            LOGGER.info("Empty Condition unfortunately!!!");
            return new ArrayList<>();
        }

        return seats.stream().map(x -> to(x)).collect(Collectors.toList());

    }
}
