package com.mpk.services;

import com.mpk.dao.BusLineRepository;
import com.mpk.entity.Bus;
import com.mpk.entity.BusLine;
import com.mpk.excepctions.BusLineNotFoundException;
import com.mpk.excepctions.ExceptionFactory;
import com.mpk.helpers.BusLineHelper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusLineService {
    private BusLineRepository busLineRepository;

    @Autowired
    public BusLineService(BusLineRepository busLineRepository) {
        this.busLineRepository = busLineRepository;
    }

    public List<BusLineHelper> getAll(){
        List<BusLine> busLines = busLineRepository.findAll();
        List<BusLineHelper> lineHelpers = new ArrayList<>();
        for(BusLine b: busLines){
            lineHelpers.add(new BusLineHelper(b));
        }
        return lineHelpers;
    }

    public BusLineHelper findById(Long id) {
        BusLine busLine = busLineRepository.findOne(id);
        ExceptionFactory.throwNotFoundExceptionIfNull(busLine, BusLine.class);
        return new BusLineHelper(busLine);
    }

    public void add(BusLineHelper busLine){
        BusLine b = new BusLine();
        b.setName(busLine.getName());
        busLineRepository.save(b);
    }

    public void update(BusLineHelper busLineHelper) {
        BusLine busLine = busLineRepository.findOne(busLineHelper.getId());
        ExceptionFactory.throwNotFoundExceptionIfNull(busLine, BusLine.class);
        busLine.setName(busLineHelper.getName());
        busLineRepository.save(busLine);
    }

    public void delete(Long id) {
        BusLine busLine = busLineRepository.findOne(id);
        ExceptionFactory.throwNotFoundExceptionIfNull(busLine, BusLine.class);
        busLineRepository.delete(busLine);
    }

}
