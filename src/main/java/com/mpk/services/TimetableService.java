package com.mpk.services;

import com.mpk.dao.BusLineRepository;
import com.mpk.dao.TimetableRepository;
import com.mpk.entity.BusLine;
import com.mpk.entity.Timetable;
import com.mpk.enums.DayKind;
import com.mpk.enums.Direction;
import com.mpk.services.timetable.TimetableAtBusStopFlyweight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Observable;

/**
 * Created by Bartosz Piśkiewicz on 18.12.2016.
 */
@Service
public class TimetableService extends Observable {

    private TimetableRepository timetableRepository;
    private BusLineRepository busLineRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository, TimetableAtBusStopFlyweight timetableAtBusStopFlyweight, BusLineRepository busLineRepository) {
        this.timetableRepository = timetableRepository;
        this.addObserver(timetableAtBusStopFlyweight);
        this.busLineRepository = busLineRepository;
    }

    public void create(Timetable timetable){
        timetableRepository.save(timetable);
        this.notifyObservers();
    }

    public void edit(Timetable timetable){
        timetableRepository.save(timetable);
        this.notifyObservers();
    }

    public void delete(Timetable timetable){
        timetableRepository.delete(timetable);
        this.notifyObservers();
    }

    public List<Timetable> findAll(){
        return timetableRepository.findAll();
    }

    public Timetable findById(long id){
        return timetableRepository.findOne(id);
    }

    public List<Timetable> findByBusLineIdDirectionAndDayKind(long busLineId, Direction direction, DayKind dayKind){
        BusLine busLine = busLineRepository.findOne(busLineId);
        Sort sort = new Sort(Sort.Direction.ASC, "departureTime");
        return this.timetableRepository.findByBusLineAndDirectionAndDayKind(busLine, direction, dayKind, sort);
    }

}
