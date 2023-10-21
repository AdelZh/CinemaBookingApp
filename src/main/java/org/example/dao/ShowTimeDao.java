package org.example.dao;

import org.example.model.ShowTime;

import java.sql.Time;
import java.util.List;

public interface ShowTimeDao {

    ShowTime saveShow(ShowTime showTime);
    void assign(ShowTime showTime);
    void cleanShowTimeTable();
    List<ShowTime> getAll();
    ShowTime findById(Long showTimeId);
    String deleteShowTimeByStartAndEndTime(Time startTime, Time endTime);


}
