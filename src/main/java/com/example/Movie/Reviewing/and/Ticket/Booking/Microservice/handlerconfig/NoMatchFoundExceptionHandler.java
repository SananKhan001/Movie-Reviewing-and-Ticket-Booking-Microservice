package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.handlerconfig;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.NoMatchFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoMatchFoundExceptionHandler {

    @ExceptionHandler(NoMatchFoundException.class)
    public ResponseEntity handlerNoMatchFoundException(NoMatchFoundException ex){

        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);

    }

}
