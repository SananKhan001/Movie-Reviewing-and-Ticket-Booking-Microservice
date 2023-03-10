package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Genre;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponse {

    private Genre genre;
    private String title;
    private Double rating;
    private Long numberOfReviews;
    private List<ReviewResponse> reviews;

}
