package org.example.service.Impl;

import org.example.dao.Impl.MovieDaoImpl;
import org.example.dao.Impl.ShowTimeDaoImpl;
import org.example.dao.Impl.TheatreDaoImpl;
import org.example.dao.MovieDao;
import org.example.dao.ShowTimeDao;
import org.example.dao.TheatreDao;
import org.example.model.Movie;
import org.example.model.ShowTime;
import org.example.model.Theatre;
import org.example.service.ShowTimeService;

import java.sql.Time;
import java.util.List;

public class ShowTimeServiceImpl implements ShowTimeService {

    ShowTimeDao showTimeDao=new ShowTimeDaoImpl();
    MovieDao movieDao = new MovieDaoImpl();
    TheatreDao theatreDao = new TheatreDaoImpl();

    @Override
    public ShowTime saveShow(ShowTime showTime) {
        return showTimeDao.saveShow(showTime);
    }


    @Override
    public String assign(Long showTime_id, Long movie_id, Long theatre_id) {
        ShowTime showTime = findById(showTime_id);
        Movie movie = movieDao.findById(movie_id);
        Theatre theatre = theatreDao.findById(theatre_id);
        showTime.setMovie_id(movie.getId());
        showTime.setTheatre_id(theatre.getId());
        showTimeDao.assign(showTime);
        return "success";
    }

    @Override
    public List<ShowTime> getAll() {
        return showTimeDao.getAll();
    }

    @Override
    public ShowTime findById(Long showTimeId) {
        return showTimeDao.findById(showTimeId);
    }

    @Override
    public String deleteShowTimeByStartAndEndTime(Time startTime, Time endTime) {
        return showTimeDao.deleteShowTimeByStartAndEndTime(startTime,endTime);
    }

    @Override
    public void cleanShowTimeTable() {
        showTimeDao.cleanShowTimeTable();
    }
}
