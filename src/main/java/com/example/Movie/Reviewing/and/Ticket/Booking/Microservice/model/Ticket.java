package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
public class Ticket {

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
    @JsonIgnore
    private Show show;

    @OneToMany(mappedBy = "ticket")
    @JsonIgnore
    private List<ShowSeat> seats;

}
