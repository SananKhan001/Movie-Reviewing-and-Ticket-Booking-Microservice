package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie_table")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Movie {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    // a single entity which is average of all reviews for a movie
    private Double rating;

    @OneToMany
    private List<Review> reviews;
}
