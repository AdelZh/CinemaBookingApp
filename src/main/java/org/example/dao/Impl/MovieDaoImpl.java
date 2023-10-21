package org.example.dao.Impl;

import org.example.config.Config;
import org.example.dao.MovieDao;
import org.example.model.Genre;
import org.example.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieDaoImpl implements MovieDao {

    Connection connection= Config.getConnection();

    @Override
    public List<Movie> searchByName(String title) {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movie WHERE title = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException("Not found");
            }
            do {
                Movie movie = new Movie();
                movie.setId(resultSet.getLong("id"));
                movie.setTitle(title);
                movie.setGenre(Genre.valueOf(resultSet.getString("genre")));
                movie.setDuration(resultSet.getInt("duration"));
                movies.add(movie);
            } while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movies;
    }


    @Override
    public Map<Genre, List<Movie>> getMoviesByGenre(String genre) {
        Map<Genre, List<Movie>> map=new HashMap<>();
        PreparedStatement preparedStatement;
        String sql="select * from movie where genre=?";
        try {
            preparedStatement=connection.prepareStatement(sql);
            for (Genre genreEnum : Genre.values()) {
                map.put(genreEnum, new ArrayList<>());
            }
            preparedStatement.setString(1, genre);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (!resultSet.next()){
                throw new RuntimeException("Not founded");
            }else {
                Movie movie=new Movie();
                movie.setId(resultSet.getLong("id"));
                movie.setTitle(resultSet.getString("title"));
                String genreString=resultSet.getString("genre");
                Genre movigenre=Genre.valueOf(genreString);
                movie.setGenre(movigenre);
                movie.setDuration(resultSet.getInt("duration"));
                map.get(movigenre).add(movie);
                preparedStatement.close();
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return map;
    }

    @Override
    public List<Movie> sortByDuration(String ascOrDesc) {
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        String sortOrder = ascOrDesc.equals("DESC") ? "DESC" : "ASC";
        String sql = "SELECT m.id, m.title, m.genre, m.duration FROM movie m " +
                "ORDER BY m.duration " + sortOrder;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                movie.setId(resultSet.getLong("id"));
                movie.setTitle(resultSet.getString("title"));
                String genreString = resultSet.getString("genre");
                Genre movigenre = Genre.valueOf(genreString);
                movie.setGenre(movigenre);
                movie.setDuration(resultSet.getInt("duration"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return movies;

    }


    @Override
    public List<Movie> getMoviesByTheaterIdAndStartTime(Long theaterId, Time startTime) {
        String sql="SELECT m.* FROM movie m " +
                "INNER JOIN show_time s ON m.id = s.movie_id " +
                "WHERE s.theatre_id = ? AND s.start_time = ?";
       List<Movie> movies=new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(sql);
           preparedStatement.setLong(1, theaterId);
           preparedStatement.setTime(2, startTime);
           ResultSet resultSet=preparedStatement.executeQuery();
           if (!resultSet.next()){
               throw new RuntimeException("Not found");
           }else {
               Movie movie=new Movie();
               movie.setId(resultSet.getLong("id"));
               movie.setTitle(resultSet.getString("title"));
               String genreString = resultSet.getString("genre");
               Genre genre = Genre.valueOf(genreString);
               movie.setGenre(genre);
               movie.setDuration(resultSet.getInt("duration"));
               movies.add(movie);
               preparedStatement.close();
           }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return movies;
    }

    @Override
    public void createMovie(String table, List<String> strings) {
        StringBuilder stringBuilder =
                new StringBuilder(String.format("create table %s (", table));
        try {
            Statement statement = connection.createStatement();
            for (int i = 0; i < strings.size(); i++) {
                stringBuilder.append(strings.get(i));
                if (i < strings.size() - 1) {
                    stringBuilder.append(",");
                }
            }
            stringBuilder.append(")");
            statement.executeUpdate(stringBuilder.toString());
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public String saveMovie(Movie movie) {
        String sql="insert into movie (id, title, genre, duration)"+
                "values(?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1, movie.getId());
            preparedStatement.setString(2, movie.getTitle());
            preparedStatement.setString(3, movie.getGenre().toString());
            preparedStatement.setInt(4, movie.getDuration());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return "saved";
    }





    @Override
    public Movie findById(Long id) {
       String sql="select * from movie where id=?";
       Movie movie=new Movie();
       PreparedStatement preparedStatement;
       try {
           preparedStatement=connection.prepareStatement(sql);
           preparedStatement.setLong(1, id);
           ResultSet resultSet=preparedStatement.executeQuery();
           if (!resultSet.next()){
               throw new RuntimeException("Not found");
           }else {
               movie.setId(resultSet.getLong("id"));
               movie.setTitle(resultSet.getString("title"));
               String genreString = resultSet.getString("genre");
               Genre genre = Genre.valueOf(genreString);
               movie.setGenre(genre);
               movie.setDuration(resultSet.getInt("duration"));
               preparedStatement.close();
           }
       }catch (SQLException e){
           throw new RuntimeException(e);
       }
       return movie;
    }

    @Override
    public void deleteDurationColumn() {
        String sql="alter table movie drop column duration";

        try (PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
