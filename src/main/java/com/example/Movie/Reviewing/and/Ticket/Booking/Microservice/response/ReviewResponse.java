package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {

    private String movieReview;

    private Double rating;

}
