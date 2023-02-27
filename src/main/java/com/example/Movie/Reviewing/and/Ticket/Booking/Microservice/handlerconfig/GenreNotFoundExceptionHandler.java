package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.handlerconfig;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.GenreNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenreNotFoundExceptionHandler {

    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity handlerGenreNotFoundException(GenreNotFoundException ex){

        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);

    }

}
