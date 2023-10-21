package org.example;

import org.example.config.Config;
import org.example.model.*;
import org.example.service.*;
import org.example.service.Impl.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {

        Config.getConnection();
        MovieService movieService = new MovieServiceIml();
        TheatreService theatreService = new TheatreServiceImpl();
        UserService userService = new UserServiceImpl();
        BookingService bookingService = new BookingServiceImpl();
        ShowTimeService showTimeService = new ShowTimeServiceImpl();

        Scanner scanner1 = new Scanner(System.in);
        while (true) {
            System.out.println("1  Create table movie");
            System.out.println("2  Save movie");
            System.out.println("3  Find movie by id");
            System.out.println("4  Search movie by name");
            System.out.println("5  Get movies by genre");
            System.out.println("6  Create table theatre");
            System.out.println("7  Save theatre");
            System.out.println("8  Find theatre by id");
            System.out.println("9  Create user");
            System.out.println("10 Save user");
            System.out.println("11 Find user by email");
            System.out.println("12 Update table user");
            System.out.println("13 Insert value for new column in user table ");
            System.out.println("14 Create table show_time");
            System.out.println("15 Create table Booking");
            System.out.println("16 Save show");
            System.out.println("17 Find show by id");
            System.out.println("18 deleteShowTimeByStartAndEndTime");
            System.out.println("19 Get all shows");
            System.out.println("20 Save bookings");
            System.out.println("21 Find by id");
            System.out.println("22 Get booking by user id");
            System.out.println("23 Get all bookings");
            System.out.println("24 Sort movies by duration ");
            System.out.println("25 getMoviesByTheaterIdAndStartTime");
            System.out.println("26 Assign show_time");
            System.out.println("27 Clean booking table");
            System.out.println("28 Remove user by id");
            System.out.println("29 Clean show_time table");
            System.out.println("30 Delete duration column from movie");
            System.out.println("31 Exit");
            int option = scanner1.nextInt();
            switch (option) {
                case 1 -> movieService.createMovie("Movie", List.of(
                        "id serial primary key",
                        "title varchar",
                        "genre varchar",
                        "duration int"
                ));
                case 2 -> {
                    Movie movie = new Movie(1L, "The sun", Genre.COMEDY, 5);
                    Movie movie1 = new Movie(2L, "Rush Hour", Genre.COMEDY, 3);
                    Movie movie2 = new Movie(3L, "Golden Hour", Genre.HISTORICAL, 4);
                    System.out.println(movieService.saveMovie(movie));
                    System.out.println(movieService.saveMovie(movie1));
                    System.out.println(movieService.saveMovie(movie2));

                }
                case 3 -> System.out.println(movieService.findById(1L));
                case 4 -> System.out.println(movieService.searchByName("The sun"));
                case 5 -> System.out.println(movieService.getMoviesByGenre("COMEDY"));
                case 6 -> movieService.createMovie("Theatre", List.of(
                        "id serial primary key",
                        "name varchar",
                        "location varchar"
                ));
                case 7 -> {
                    Theatre theatre = new Theatre(1L, "Cinema", "Dubai");
                    Theatre theatre1 = new Theatre(2L, "Movie", "Korea");
                    Theatre theatre2 = new Theatre(3L, "Screen", "USA");
                    System.out.println(theatreService.saveTheatre(theatre));
                    System.out.println(theatreService.saveTheatre(theatre1));
                    System.out.println(theatreService.saveTheatre(theatre2));
                }
                case 8 -> System.out.println(theatreService.findById(1L));
                case 9 -> movieService.createMovie("Users", List.of(
                        "id serial primary key",
                        "userName varchar",
                        "password int",
                        "email varchar"
                ));
                case 10 -> {
                    User user = new User(1L, "Adel", 123, "adel@gmail.com");
                    User user1 = new User(2L, "Alina", 456, "alina@gmail.com");
                    User user2 = new User(3L, "Tunuk", 789, "tun@gmail.com");
                    System.out.println(userService.saveUser(user));
                    System.out.println(userService.saveUser(user1));
                    System.out.println(userService.saveUser(user2));
                }
                case 11 -> System.out.println(userService.existByEmail("adel@gmail.com"));
                case 12 -> userService.updateTableUser("language", "VARCHAR");
                case 13 -> System.out.println(userService.saveLanguage(3L, "English"));
                case 14 -> movieService.createMovie("Show_Time", List.of(
                        "id serial primary key",
                        "movie_id integer references movie(id)",
                        "theatre_id integer references theatre(id)",
                        "start_time time",
                        "end_time time"
                ));
                case 15 -> movieService.createMovie("Booking", List.of(
                        "id serial primary key",
                        "show_time_id integer references show_time(id)",
                        "users_id integer references users(id)",
                        "number_of_tickets int",
                        "booking_time timestamp"
                ));
                case 16 -> {
                    Time startTime = Time.valueOf("20:00:00");
                    Time endTime = Time.valueOf("21:00:00");
                    Time startTime1 = Time.valueOf("18:00:00");
                    Time endTime1 = Time.valueOf("21:00:00");
                    ShowTime showTime = new ShowTime(1L, 1L, 1L, startTime, endTime);
                    ShowTime showTime1 = new ShowTime(2L, 2L, 2L, startTime1, endTime1);
                    ShowTime showTime11 = new ShowTime(3L, 3L, 3L, startTime1, endTime1);
                    System.out.println(showTimeService.saveShow(showTime));
                    System.out.println(showTimeService.saveShow(showTime1));
                    System.out.println(showTimeService.saveShow(showTime11));

                }
                case 17 -> System.out.println(showTimeService.findById(1L));
                case 18 -> {
                    Time startTime2 = Time.valueOf("20:00:00");
                    Time endTime2 = Time.valueOf("21:00:00");
                    System.out.println(showTimeService.deleteShowTimeByStartAndEndTime(startTime2, endTime2));
                }
                case 19 -> System.out.println(showTimeService.getAll());
                case 20 -> {
                    LocalDateTime bookingTime = LocalDateTime.parse("2022-03-12T20:00:00");
                    LocalDateTime bookingTime2 = LocalDateTime.parse("2022-03-12T18:00:00");
                    Booking booking = new Booking(1L, 2, 1, 23, bookingTime);
                    Booking booking1 = new Booking(2L, 3, 2, 4, bookingTime2);
                    System.out.println(bookingService.save(booking));
                    System.out.println(bookingService.save(booking1));
                }
                case 21 -> System.out.println(bookingService.findById(1L));
                case 22 -> System.out.println(bookingService.getBookingByUserId(2L));
                case 23 -> bookingService.getAllBookings();
                case 24 -> System.out.println(movieService.sortByDuration("ASC"));
                case 25 ->{
                Time startTime1 = Time.valueOf("20:00:00");
                        movieService.getMoviesByTheaterIdAndStartTime(1L, startTime1);}
                case 26 -> System.out.println(showTimeService.assign(1L, 2L, 3L));
                case 27 -> bookingService.cleanBookingTable();
                case 28 -> userService.removeUserById(1L);
                case 29 -> showTimeService.cleanShowTimeTable();
                case 30 -> movieService.deleteDurationColumn();
                case 31 -> System.exit(0);

            }


        }

    }
}




