package org.example.service.Impl;

import org.example.dao.Impl.TheatreDaoImpl;
import org.example.dao.TheatreDao;
import org.example.model.Movie;
import org.example.model.Theatre;
import org.example.service.TheatreService;

import java.util.List;
import java.util.Map;

public class TheatreServiceImpl implements TheatreService {




    TheatreDao theatreDao=new TheatreDaoImpl();


    @Override
    public String saveTheatre(Theatre theatre) {
        return theatreDao.saveTheatre(theatre);
    }

    @Override
    public List<Map<Movie, List<Theatre>>> getAllMoviesByTime(int hour) {
        return null;
    }

    @Override
    public Theatre findById(Long theatreId) {
        return theatreDao.findById(theatreId);
    }
}
