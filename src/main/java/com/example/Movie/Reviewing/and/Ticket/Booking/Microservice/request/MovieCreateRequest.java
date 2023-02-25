package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Genre;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Movie;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieCreateRequest {

    @NotNull(message = "Given Title of Movie is NULL")
    @NotBlank(message = "Given Title of Movie is Blank")
    private String title;

    @NotBlank(message = "Given genre is blank")
    @NotNull(message = "Given genre is NULL")
    private Genre genre;

    public Movie to(){
        return Movie.builder()
                .genre(this.genre)
                .title(this.title).build();
    }
}
