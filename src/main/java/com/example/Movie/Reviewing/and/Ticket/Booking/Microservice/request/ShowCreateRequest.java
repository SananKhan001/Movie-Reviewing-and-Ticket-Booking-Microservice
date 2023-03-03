package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Show;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ShowCreateRequest {

    @NotNull(message = "Show Time is Mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime showTime;

    @NotNull(message = "Movie is Mandatory for Show")
    private long movieId;

    @NotNull(message = "Theater is Mandatory for Show")
    private long theaterId;

    public Show to(){

        return Show.builder()
                .showTime(this.showTime)
                .build();

    }

}
