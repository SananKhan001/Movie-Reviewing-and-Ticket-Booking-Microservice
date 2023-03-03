package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Theater;
import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TheaterCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    public Theater to(){
        return Theater.builder()
                .address(this.address)
                .city(this.city)
                .name(this.name)
                .build();
    }

}
