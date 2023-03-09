package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.MovieResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.validation.annotation.Validated;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "movie_table")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    // a single entity which is average of all reviews for a movie
    private Double rating;

    private Long numberOfReviews;

    @OneToMany(mappedBy = "movie")
    @JsonIgnoreProperties("movie")
    private List<Review> reviews;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<Show> shows;



    public MovieResponse to(){
        return MovieResponse.builder()
                .genre(this.genre)
                .rating(this.rating)
                .reviews(Review.to(reviews))
                .title(this.title)
                .numberOfReviews(this.getNumberOfReviews())
                .build();
    }

}
