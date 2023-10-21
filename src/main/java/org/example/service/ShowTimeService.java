package org.example.service;

import org.example.model.ShowTime;

import java.sql.Time;
import java.util.List;

public interface ShowTimeService {

    ShowTime saveShow(ShowTime showTime);

    String assign(Long showTime_id, Long movie_id, Long theatre_id);
    void cleanShowTimeTable();
    List<ShowTime> getAll();
    ShowTime findById(Long showTimeId);
    String deleteShowTimeByStartAndEndTime(Time startTime, Time endTime);

}
