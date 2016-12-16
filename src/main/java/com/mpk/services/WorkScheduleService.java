package com.mpk.services;

import com.mpk.dao.BusRepository;
import com.mpk.dao.WorkScheduleRepository;
import com.mpk.entity.Bus;
import com.mpk.entity.WorkSchedule;
import com.mpk.enums.BusStatus;
import com.mpk.excepctions.BusNotAllowedException;
import com.mpk.excepctions.ExceptionFactory;
import com.mpk.helpers.WorkScheduleHelper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkScheduleService {
    private final WorkScheduleRepository workScheduleRepository;
    private final BusRepository busRepository;

    @Autowired
    public WorkScheduleService(WorkScheduleRepository workScheduleRepository, BusRepository busRepository) {
        this.workScheduleRepository = workScheduleRepository;
        this.busRepository = busRepository;
    }

    @SneakyThrows
    public void add(WorkScheduleHelper wsh) {
        if(!isPossibleAddBus(wsh)) {
            throw new BusNotAllowedException("Unable to create WorkSchedule with defined Bus");
        }
        int hours = calculateHours(wsh.getWorkBegin(), wsh.getWorkEnd());
        WorkSchedule workSchedule = WorkSchedule
                .builder()
                .workBegin(wsh.getWorkBegin())
                .workEnd(wsh.getWorkEnd())
                .hours(hours)
                //.driver(workScheduleHelper.getDriver())
                .bus(busRepository.findOne(wsh.getBusHelper().getId()))
                .build();
        workScheduleRepository.save(workSchedule);
    }

    private boolean isPossibleAddBus(WorkScheduleHelper wsh) {
        Bus bus = busRepository.findOne(wsh.getBusHelper().getId());
        ExceptionFactory.throwNotFoundExceptionIfNull(bus, Bus.class);
        List<Bus> buses = freeBuses(wsh.getWorkBegin(), wsh.getWorkEnd());

        if (buses.contains(bus)) {
            return true;
        }
        return false;
    }

    private List<Bus> freeBuses(LocalDateTime workBegin, LocalDateTime workEnd) {
        List<WorkSchedule> inRange = workScheduleRepository.findByWorkBeginGreaterThanEqualAndWorkEndLessThanEqual(workBegin, workEnd);
        List<Bus> busesInRange = new ArrayList<>();
        inRange.forEach((workSchedule -> busesInRange.add(workSchedule.getBus())));
        List<Bus> freeBuses = busRepository.findByStatus(BusStatus.Active);
        busesInRange.forEach(freeBuses::remove);
        freeBuses.forEach(bus -> System.out.println(bus.getId()));
        return freeBuses;
    }

    private int calculateHours(LocalDateTime workBegin, LocalDateTime workEnd) {
        LocalDateTime localDateTime = LocalDateTime.of(workEnd.getYear(), workEnd.getMonth(), workEnd.getDayOfMonth(), workEnd.getHour(), workEnd.getMinute());
        localDateTime = localDateTime.plusMinutes(workBegin.getMinute() * -1);
        localDateTime = localDateTime.plusHours(workBegin.getHour() * -1);
        localDateTime = localDateTime.plusDays(workBegin.getDayOfMonth() * -1);
        localDateTime = localDateTime.plusMonths(workBegin.getMonthValue() * -1);
        localDateTime = localDateTime.plusYears(workBegin.getYear() * -1);
        return localDateTime.getHour();
    }

    public List<WorkScheduleHelper> findAll() {
        List<WorkSchedule> workSchedules = workScheduleRepository.findAll();
        List<WorkScheduleHelper> workScheduleHelpers = new ArrayList<>();
        workSchedules.forEach((ws -> workScheduleHelpers.add(new WorkScheduleHelper(ws))));
        return workScheduleHelpers;
    }
}
