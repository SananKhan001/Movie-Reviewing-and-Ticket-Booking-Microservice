package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.TheaterResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "theaters")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "address",nullable = false)
    private String address;

    @OneToMany(mappedBy = "theater")
    @JsonIgnoreProperties("theater")
    private List<Show> shows;

    @OneToMany(mappedBy = "theater")
    @JsonIgnoreProperties("theater")
    private List<TheaterSeats> seats;

    public TheaterResponse to() {

        return TheaterResponse.builder()
                .id(this.id)
                .address(this.address)
                .city(this.city)
                .name(this.name)
                .build();

    }
}
