package org.example.model;

import java.sql.Time;


public class ShowTime {
    private Long id;
    private Long movie_id;
    private Long theatre_id;
    private Time start_time;
    private Time end_time;

    public ShowTime() {
    }

    public ShowTime(Long id,Long movie_id, Long theatre_id,Time start_time, Time end_time) {
        this.id=id;
        this.movie_id = movie_id;
        this.theatre_id = theatre_id;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Long movie_id) {
        this.movie_id = movie_id;
    }

    public Long getTheatre_id() {
        return theatre_id;
    }

    public void setTheatre_id(Long theatre_id) {
        this.theatre_id = theatre_id;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "ShowTime{" +
                "id=" + id +
                ", movie_id=" + movie_id +
                ", theatre_id=" + theatre_id +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }
}
