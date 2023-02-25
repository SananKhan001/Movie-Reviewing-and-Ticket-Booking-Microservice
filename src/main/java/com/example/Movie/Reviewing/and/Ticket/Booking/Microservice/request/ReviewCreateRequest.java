package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request;


import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Review;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewCreateRequest {

    private String movieReview;

    @Positive(message = "Given rating is negative or zero")
    @NotNull(message = "Given rating is null")
    private Double rating;

    public Review to(){
        return Review.builder()
                .movieReview(this.movieReview)
                .rating(this.rating).build();
    }

}
