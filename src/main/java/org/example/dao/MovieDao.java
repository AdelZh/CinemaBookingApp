package org.example.dao;

import org.example.model.Genre;
import org.example.model.Movie;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public interface MovieDao {

    List<Movie> searchByName(String title);

    Map<Genre, List<Movie>> getMoviesByGenre(String genre);
    List<Movie> sortByDuration(String ascOrDesc);
    List<Movie> getMoviesByTheaterIdAndStartTime(Long theaterId, Time startTime);

    void createMovie(String table, List<String> strings);

    String saveMovie(Movie movie);

    Movie findById(Long id);

     void deleteDurationColumn();


}
