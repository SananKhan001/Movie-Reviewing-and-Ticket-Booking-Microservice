package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.handlerconfig;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.RatingLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RatingLimitExceededExceptionHandler {

    @ExceptionHandler(RatingLimitExceededException.class)
    public ResponseEntity handlerRatingLimitExceededException(RatingLimitExceededException ex){

        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);

    }

}
