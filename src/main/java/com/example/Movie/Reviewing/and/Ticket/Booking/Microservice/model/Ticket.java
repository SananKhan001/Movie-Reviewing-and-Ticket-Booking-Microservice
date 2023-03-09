package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.TicketResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Builder
@Getter
@Setter
@ToString
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "alloted_seats",nullable = false)
    private String allotedSeats;

    @Column(name = "amount",nullable = false)
    private double amount;

    @CreationTimestamp
    @Column(name = "booked_at",nullable = false)
    private Date bookedAt;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("tickets")
    private Show show;

    @OneToMany(mappedBy = "ticket")
    @JsonIgnoreProperties("ticket")
    private List<ShowSeat> seats;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("ticketList")
    private Customer customer;

    public TicketResponse to(){
        return TicketResponse.builder()
                .id(this.id)
                .allottedSeats(this.allotedSeats)
                .amount(this.amount)
                .bookedAt(this.bookedAt)
                .movieName(this.show.getMovie().getTitle())
                .showTime(this.show.getShowTime())
                .theaterName(this.getShow().getTheater().getName())
                .address(this.getShow().getTheater().getAddress())
                .build();
    }

}
