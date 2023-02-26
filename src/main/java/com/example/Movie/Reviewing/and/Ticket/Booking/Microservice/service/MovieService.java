package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.GenreNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.IdNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.NoMatchFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.TitleNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Genre;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.Movie;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.MovieRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.MovieCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    public MovieResponse addMovie(MovieCreateRequest movieCreateRequest) {

        Movie movie = movieCreateRequest.to();

        movie = movieRepository.save(movie);

        return movie.to();

    }

    public void addMovie(Movie movie){
        movieRepository.save(movie);
    }

    public MovieResponse findMovie(String title) throws TitleNotFoundException {

        Movie movie = movieRepository.findByTitle(title);

        if(movie == null){
            throw new TitleNotFoundException("Given title is not present");
        }

        return movie.to();

    }

    public List<MovieResponse> findMovieByGenre(String genre, int top) throws NoMatchFoundException, GenreNotFoundException {

        if (Arrays.stream(Genre.values()).noneMatch(g -> g.toString().equals(genre))){
            throw new GenreNotFoundException("Given genre is not present");
        }

        List<Movie> movies = movieRepository.findByGenre(Genre.valueOf(genre));

        if(movies == null || movies.isEmpty()){
            throw new NoMatchFoundException("There is not any movie matching with given Genre");
        }

        List<MovieResponse> movieResponses = movies
                .stream()
                .sorted(Comparator.comparing(Movie::getRating,Comparator.reverseOrder()))
                .map(Movie::to).collect(Collectors.toList());

        if(movieResponses.size() > top){
            return movieResponses.subList(0,top);
        }

        return movieResponses;

    }

    public Optional<Movie> findById(Long movieId) throws IdNotFoundException {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if(movie == null || movie.isEmpty()){
            throw new IdNotFoundException("Given Id is not found");
        }
        return movie;
    }
}
