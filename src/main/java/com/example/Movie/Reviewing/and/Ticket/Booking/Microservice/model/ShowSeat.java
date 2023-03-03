package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.ShowSeatsResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    @JsonIgnore
    private Show show;

    @ManyToOne
    @JsonIgnore
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

        if(!seats.isEmpty()){
            return seats.stream().map(x -> to(x)).collect(Collectors.toList());
        }

        return new ArrayList<>();

    }
}
