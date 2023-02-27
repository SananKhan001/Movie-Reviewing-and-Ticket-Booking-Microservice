package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception;

public class RatingLimitExceededException extends Exception {
    public RatingLimitExceededException(String message) {
        super(message);
    }
}
