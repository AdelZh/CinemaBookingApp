package org.example.dao.Impl;

import org.example.config.Config;
import org.example.dao.ShowTimeDao;
import org.example.model.ShowTime;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowTimeDaoImpl implements ShowTimeDao {

    Connection connection = Config.getConnection();

    @Override
    public ShowTime saveShow(ShowTime showTime) {
        String sql = "insert into show_time(id, movie_id, theatre_id, start_time, end_time)" +
                "values(?,?,?,?,?)";
        ShowTime showTime1 = new ShowTime();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, showTime.getId());
            preparedStatement.setLong(2, showTime.getMovie_id());
            preparedStatement.setLong(3, showTime.getTheatre_id());
            preparedStatement.setTime(4, showTime.getStart_time());
            preparedStatement.setTime(5, showTime.getEnd_time());
            preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return showTime1;

    }



    @Override
    public void assign(ShowTime showTime) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("""
                             update show_time set  movie_id=?,
                             theatre_id=?
                             where id=?""");

            preparedStatement.setLong(1, showTime.getMovie_id());
            preparedStatement.setLong(2, showTime.getTheatre_id());
            preparedStatement.setLong(3, showTime.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ShowTime> getAll() {
        List<ShowTime> showTimes=new ArrayList<>();
        String sql="select * from show_time";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                ShowTime showTime=new ShowTime();
                showTime.setId(resultSet.getLong("id"));
                showTime.setMovie_id(resultSet.getLong("movie_id"));
                showTime.setTheatre_id(resultSet.getLong("theatre_id"));
                showTime.setStart_time(resultSet.getTime("start_time"));
                showTime.setEnd_time(resultSet.getTime("end_time"));
                showTimes.add(showTime);

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return showTimes;
    }

    @Override
    public ShowTime findById(Long showTimeId) {
        String sql="select * from show_time where id=?";
        ShowTime showTime=new ShowTime();
        PreparedStatement preparedStatement;
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1, showTimeId);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(!resultSet.next()){
                throw new RuntimeException("Not founded");
            }else {
                showTime.setId(resultSet.getLong("id"));
                showTime.setMovie_id(resultSet.getLong("movie_id"));
                showTime.setTheatre_id(resultSet.getLong("theatre_id"));
                showTime.setStart_time(resultSet.getTime("start_time"));
                showTime.setEnd_time(resultSet.getTime( "end_time"));

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return showTime;
    }

    @Override
    public String deleteShowTimeByStartAndEndTime(Time startTime, Time endTime) {
        String sql="delete from show_time where start_time=? and end_time=?";
        PreparedStatement preparedStatement;
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setTime(1, startTime);
            preparedStatement.setTime(2, endTime);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return "Deleted";
    }


    @Override
    public void cleanShowTimeTable() {
        String sql="delete from show_time";

        try (PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.executeUpdate();
            System.out.println("Table show_time successfully cleaned");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
