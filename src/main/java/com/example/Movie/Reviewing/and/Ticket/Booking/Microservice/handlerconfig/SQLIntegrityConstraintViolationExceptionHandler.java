package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.handlerconfig;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.RatingLimitExceededException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class SQLIntegrityConstraintViolationExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity handlerSQLIntegrityConstraintViolation(SQLIntegrityConstraintViolationException ex){

        return new ResponseEntity("UserName or Email Already Exists", HttpStatus.BAD_REQUEST);

    }

}
