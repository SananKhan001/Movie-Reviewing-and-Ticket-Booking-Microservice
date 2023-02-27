package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.handlerconfig;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.TitleNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TitleNotFoundExceptionHandler {

    @ExceptionHandler(TitleNotFoundException.class)
    public ResponseEntity handlerTitleNotFoundException(TitleNotFoundException ex){

        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);

    }

}
