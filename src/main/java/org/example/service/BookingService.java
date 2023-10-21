package org.example.service;

import org.example.model.Booking;

import java.util.List;

public interface BookingService {


    String save(Booking booking);
    Booking findById(Long BookingId);
    void getAllBookings();
    List<Booking> getBookingByUserId(Long userId);
    void cleanBookingTable();
}
