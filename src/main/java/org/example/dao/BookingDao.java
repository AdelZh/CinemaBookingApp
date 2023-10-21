package org.example.dao;

import org.example.model.Booking;

import java.util.List;

public interface BookingDao {

    String save(Booking booking);

     void cleanBookingTable();
    Booking findById(Long BookingId);
    void getAllBookings();
    List<Booking> getBookingByUserId(Long userId);
}
