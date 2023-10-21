package org.example.model;

import java.time.LocalDateTime;

public class Booking {

    private Long id;

    private int show_time_id;
    private int user_id;
    private int number_of_tickets;
    private LocalDateTime booking_time;


    public Booking(){

    }

    public Booking(Long id, int show_time_id, int user_id, int number_of_tickets, LocalDateTime booking_time) {
        this.id = id;
        this.show_time_id = show_time_id;
        this.user_id = user_id;
        this.number_of_tickets = number_of_tickets;
        this.booking_time = booking_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getShow_time_id() {
        return show_time_id;
    }

    public void setShow_time_id(int show_time_id) {
        this.show_time_id = show_time_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getNumber_of_tickets() {
        return number_of_tickets;
    }

    public void setNumber_of_tickets(int number_of_tickets) {
        this.number_of_tickets = number_of_tickets;
    }

    public LocalDateTime getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(LocalDateTime booking_time) {
        this.booking_time = booking_time;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", show_time_id=" + show_time_id +
                ", user_id=" + user_id +
                ", number_of_tickets=" + number_of_tickets +
                ", booking_time=" + booking_time +
                '}';
    }
}