package org.example.dao;

import org.example.model.Movie;
import org.example.model.Theatre;

import java.util.List;
import java.util.Map;

public interface TheatreDao {

    List<Map<Movie, List<Theatre>>> getAllMoviesByTime(int hour);
    Theatre findById(Long theatreId);

    String saveTheatre(Theatre theatre);
}
