package org.example.service.Impl;

import org.example.dao.BookingDao;
import org.example.dao.Impl.BookingDaoImpl;
import org.example.model.Booking;
import org.example.service.BookingService;

import java.awt.print.Book;
import java.util.List;

public class BookingServiceImpl implements BookingService {

    BookingDao bookingDao=new BookingDaoImpl();

    @Override
    public String save(Booking booking) {
        return bookingDao.save(booking);
    }

    @Override
    public Booking findById(Long BookingId) {
        return bookingDao.findById(BookingId);
    }

    @Override
    public void getAllBookings() {
        bookingDao.getAllBookings();
    }

    @Override
    public List<Booking> getBookingByUserId(Long userId) {
        return bookingDao.getBookingByUserId(userId);
    }

    @Override
    public void cleanBookingTable() {
        bookingDao.cleanBookingTable();
    }
}
