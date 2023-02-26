package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception;

public class RatingLimitExceededException extends Throwable {
    public RatingLimitExceededException(String message) {
        super(message);
    }
}
