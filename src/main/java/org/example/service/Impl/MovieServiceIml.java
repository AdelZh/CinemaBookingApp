package org.example.service.Impl;

import org.example.dao.Impl.MovieDaoImpl;
import org.example.dao.MovieDao;
import org.example.model.Genre;
import org.example.model.Movie;
import org.example.service.MovieService;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class MovieServiceIml implements MovieService {

    MovieDao movieDao=new MovieDaoImpl();


    @Override
    public List<Movie> searchByName(String title) {
        return movieDao.searchByName(title);
    }

    @Override
    public Map<Genre, List<Movie>> getMoviesByGenre(String genre) {
        return movieDao.getMoviesByGenre(genre);
    }

    @Override
    public List<Movie> sortByDuration(String ascOrDesc) {
        return movieDao.sortByDuration(ascOrDesc);
    }

    @Override
    public List<Movie> getMoviesByTheaterIdAndStartTime(Long theaterId, Time startTime) {
        return movieDao.getMoviesByTheaterIdAndStartTime(theaterId, startTime);
    }

    @Override
    public void createMovie(String table, List<String> strings) {
        movieDao.createMovie(table, strings);
    }

    @Override
    public String saveMovie(Movie movie) {
        return movieDao.saveMovie(movie);
    }

    @Override
    public Movie findById(Long id) {
        return movieDao.findById(id);
    }

    @Override
    public void deleteDurationColumn() {
        movieDao.deleteDurationColumn();
    }
}
