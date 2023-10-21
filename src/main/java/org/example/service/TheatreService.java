package org.example.service;

import org.example.model.Movie;
import org.example.model.Theatre;

import java.util.List;
import java.util.Map;

public interface TheatreService {

    List<Map<Movie, List<Theatre>>> getAllMoviesByTime(int hour);

    Theatre findById(Long theatreId);

     String saveTheatre(Theatre theatre);
}
