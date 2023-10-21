package org.example.dao.Impl;

import org.example.config.Config;
import org.example.dao.BookingDao;
import org.example.model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements BookingDao {

    Connection connection= Config.getConnection();


    @Override
    public String save(Booking booking) {
        String sql="insert into booking (id, show_time_id, users_id, number_of_tickets, booking_time)"+
                "values(?,?,?,?,now())";
        PreparedStatement preparedStatement;
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1, booking.getId() );
            preparedStatement.setLong(2, booking.getShow_time_id());
            preparedStatement.setInt(3, booking.getUser_id());
            preparedStatement.setInt(4, booking.getNumber_of_tickets());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return "Saved";
    }



    @Override
    public Booking findById(Long BookingId) {
        String sql="select * from booking where id=?";
        Booking booking=new Booking();
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,BookingId);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(!resultSet.next()){
                throw new RuntimeException("Not founded");
            }else {
                booking.setId(resultSet.getLong("id"));
                booking.setShow_time_id(resultSet.getInt("show_time_id"));
                booking.setUser_id(resultSet.getInt("users_id"));
                booking.setNumber_of_tickets(resultSet.getInt("number_of_tickets"));
                booking.setBooking_time(resultSet.getTimestamp("booking_time").toLocalDateTime());
                preparedStatement.close();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return booking;
    }

    @Override
    public void getAllBookings() {
        String sql="select * from booking";
        PreparedStatement preparedStatement;
        try{
            preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Booking booking=new Booking();
                booking.setId(resultSet.getLong("id"));
                booking.setShow_time_id(resultSet.getInt("show_time_id"));
                booking.setUser_id(resultSet.getInt("users_id"));
                booking.setNumber_of_tickets(resultSet.getInt("number_of_tickets"));
                booking.setBooking_time(resultSet.getTimestamp("booking_time").toLocalDateTime());

                System.out.println("Booking ID: " + booking.getId());
                System.out.println("Show Time ID: " + booking.getShow_time_id());
                System.out.println("User ID: " + booking.getUser_id());
                System.out.println("Number of Tickets: " + booking.getNumber_of_tickets());
                System.out.println("Booking Time: " + booking.getBooking_time());
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Booking> getBookingByUserId(Long userId) {
        List<Booking> bookings=new ArrayList<>();
        Booking booking=new Booking();
        String sql="select * from booking where id=?";
        PreparedStatement preparedStatement;
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(!resultSet.next()){
                throw new RuntimeException("Not founded");
            }else {
                booking.setId(resultSet.getLong("id"));
                booking.setShow_time_id(resultSet.getInt("show_time_id"));
                booking.setUser_id(resultSet.getInt("users_id"));
                booking.setNumber_of_tickets(resultSet.getInt("number_of_tickets"));
                booking.setBooking_time(resultSet.getTimestamp("booking_time").toLocalDateTime());
                bookings.add(booking);
                preparedStatement.close();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return bookings;
    }


    @Override
    public void cleanBookingTable() {
        String sql="delete from booking";

        try (PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.executeUpdate();
            System.out.println("Booking table cleaned");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}


