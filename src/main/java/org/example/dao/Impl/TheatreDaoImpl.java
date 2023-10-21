package org.example.dao.Impl;

import org.example.config.Config;
import org.example.dao.TheatreDao;
import org.example.model.Movie;
import org.example.model.Theatre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TheatreDaoImpl implements TheatreDao {


    Connection connection= Config.getConnection();

    @Override
    public String saveTheatre(Theatre theatre) {
        String sql="insert into theatre(id, name, location)"+
                "values(?,?,?)";
        PreparedStatement preparedStatement;
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1, theatre.getId());
            preparedStatement.setString(2, theatre.getName());
            preparedStatement.setString(3, theatre.getLocation());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return "Saved";
    }

    @Override
    public List<Map<Movie, List<Theatre>>> getAllMoviesByTime(int hour) {
        return null;
    }

    @Override
    public Theatre findById(Long theatreId) {
        String sql="select * from theatre where id=?";
        Theatre theatre=new Theatre();
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1, theatreId);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (!resultSet.next()){
                throw new RuntimeException("Not found");
            }else {
                theatre.setId(resultSet.getLong("id"));
                theatre.setName(resultSet.getString("name"));
                theatre.setLocation(resultSet.getString("location"));
                preparedStatement.close();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return theatre;
    }
}

