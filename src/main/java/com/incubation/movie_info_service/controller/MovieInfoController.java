package com.incubation.movie_info_service.controller;

import com.incubation.movie_info_service.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${api.bearer_token}")
    private String BEARER_TOKEN;

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {

        String url = "https://api.themoviedb.org/3/movie/" + movieId;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(BEARER_TOKEN); // Automatically adds "Bearer <token>"


        // Create an entity with headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the request with exchange()
        ResponseEntity<MovieSummary> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, MovieSummary.class);

        MovieSummary movieSummary = response.getBody();

        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }
}
