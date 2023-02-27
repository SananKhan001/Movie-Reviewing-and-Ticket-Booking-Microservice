package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.handlerconfig;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IdNotFoundExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity handlerIdNotFoundException(IdNotFoundException ex){

        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);

    }

}
