package com.incubation.movie_info_service.model;

public class Movie {

    private String movieName;
    private String movieDescription;

    public Movie(String movieName, String movieDescription) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }
}
