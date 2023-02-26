package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.IdNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.RatingLimitExceededException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Movie;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Review;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.ReviewRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.ReviewCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MovieService movieService;

    public void addReview(ReviewCreateRequest reviewCreateRequest) throws IdNotFoundException, RatingLimitExceededException {

        if(reviewCreateRequest.getRating() > 5){
            throw new RatingLimitExceededException("Keep the rating between 1 to 5");
        }

        // Checking if movie is present
        Optional<Movie> movie = movieService.findById(reviewCreateRequest.getMovieId());

        // Finding previous review of movie to calculate rating
        List<Review> reviewList = movie.get().getReviews();
        movie.get().setRating(calculateRating(reviewList,reviewCreateRequest.getRating()));

        // Creating a review and adding attaching movie with that
        Review review = reviewCreateRequest.to();
        review.setMovie(movie.get());

        // Updating movie and review
        reviewRepository.save(review);
        movieService.addMovie(movie.get());
    }

    private double calculateRating(List<Review> prvReviewList, Double newRating) {

        Long totalReviews = Long.valueOf(prvReviewList.size() + 1);

        double sum = 0;

        ListIterator<Review> it = prvReviewList.listIterator();
        while(it.hasNext()){
            sum += it.next().getRating();
        }
        sum += newRating;

        return sum/totalReviews;
    }

    public ReviewResponse getReview(Long reviewId) throws IdNotFoundException {
        Optional<Review> review = reviewRepository.findById(reviewId);

        if(review == null || review.isEmpty()){
            throw new IdNotFoundException("Given Id is not found");
        }

        return review.get().to();
    }
}
