package com.mpk;

import com.mpk.dao.BusRepository;
import com.mpk.dao.BusStopRepository;
import com.mpk.dao.UserRepository;
import com.mpk.dao.WorkScheduleRepository;
import com.mpk.entity.Bus;
import com.mpk.entity.BusStop;
import com.mpk.entity.Coordinates;
import com.mpk.entity.User;
import com.mpk.entity.WorkSchedule;
import com.mpk.enums.BusStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TestNalepa implements InitializingBean {

    @Autowired
    UserRepository userRepo;

    @Autowired
    WorkScheduleRepository workScheduleRepository;

    @Autowired
    BusRepository busRepository;
    
    @Autowired
    BusStopRepository busStopRepository;
    @Override
    public void afterPropertiesSet() throws Exception {
        //addRandomUser();
        addBusesWithWorkSchedules();
        addBusStop();




        /*removeListFromAnother();
        List<Date> dates = new ArrayList<>();
        dates.add(new Date(2016,6,18, 20, 20, 20));
        dates.add(new Date(2016,6,19, 20, 20, 20));
        dates.add(new Date(2016,6,20, 20, 20, 20));
        dates.add(new Date(2016,6,21, 20, 20, 20));
        dates.add(new Date(2016,6,22, 20, 20, 20));

1
        Date workBegin =new Date(2016,6,19, 20, 20, 20);
        Date workEnd = new Date(2016,6,21, 20, 20, 20);
*/

        // workScheduleRepository.findByWorkbeginGreaterThanEqualAndWorkendLessThanEqual();
    }

    @javax.transaction.Transactional
    private void addBusesWithWorkSchedules() {
        List<Bus> buses = new ArrayList<>();
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(50.0).north(50.0).build())
                .active(false).yearOfProduction(1990).registrationNumber("TBU1111").build());
        buses.add(Bus.builder().status(BusStatus.Broken).location(Coordinates.builder().east(60.0).north(60.0).build())
                .active(true).yearOfProduction(1991).registrationNumber("TBU1112").build());
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(70.0).north(70.0).build())
                .active(true).yearOfProduction(1992).registrationNumber("TBU1113").build());
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(80.0).north(80.0).build())
                .active(false) .yearOfProduction(1993).registrationNumber("TBU1114") .build());
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(90.0).north(90.0).build())
                .active(true).yearOfProduction(1995).registrationNumber("TBU1115") .build());
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(100.0).north(100.0).build())
                .active(true).yearOfProduction(1997).registrationNumber("TBU1116") .build());
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(120.0).north(110.0).build())
                .active(false) .yearOfProduction(1999).registrationNumber("TBU1117") .build());
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(120.0).north(120.0).build())
                .active(true) .yearOfProduction(2015).registrationNumber("TBU1118") .build());
        buses.add(Bus.builder().status(BusStatus.Active).location(Coordinates.builder().east(130.0).north(130.0).build())
                .active(false) .yearOfProduction(2016) .registrationNumber("TBU1119").build());
        busRepository.save(buses);

        List<LocalDateTime> localDateTimesBegin = new ArrayList<>();
        localDateTimesBegin.add(LocalDateTime.of(2016, 11, 23, 8, 0));
        localDateTimesBegin.add(LocalDateTime.of(2016, 11, 24, 8, 0));
        localDateTimesBegin.add(LocalDateTime.of(2016, 11, 25, 8, 0));
        localDateTimesBegin.add(LocalDateTime.of(2016, 11, 26, 8, 0));
        localDateTimesBegin.add(LocalDateTime.of(2016, 11, 26, 8, 0));
        localDateTimesBegin.add(LocalDateTime.of(2016, 11, 27, 8, 0));
        List<LocalDateTime> localDateTimesEnd = new ArrayList<>();
        localDateTimesEnd.add(LocalDateTime.of(2016, 11, 23, 16, 0));
        localDateTimesEnd.add(LocalDateTime.of(2016, 11, 24, 16, 0));
        localDateTimesEnd.add(LocalDateTime.of(2016, 11, 25, 16, 0));
        localDateTimesEnd.add(LocalDateTime.of(2016, 11, 26, 16, 0));
        localDateTimesEnd.add(LocalDateTime.of(2016, 11, 26, 16, 0));
        localDateTimesEnd.add(LocalDateTime.of(2016, 11, 27, 16, 0));
        List<WorkSchedule> workSchedules = new ArrayList<>();
        workSchedules.add(WorkSchedule.builder().workBegin(localDateTimesBegin.get(0)).workEnd(localDateTimesEnd.get(0)).bus(buses.get(0)).build());
        workSchedules.add(WorkSchedule.builder().workBegin(localDateTimesBegin.get(1)).workEnd(localDateTimesEnd.get(1)).bus(buses.get(1)).build());
        workSchedules.add(WorkSchedule.builder().workBegin(localDateTimesBegin.get(2)).workEnd(localDateTimesEnd.get(2)).bus(buses.get(2)).build());
        workSchedules.add(WorkSchedule.builder().workBegin(localDateTimesBegin.get(3)).workEnd(localDateTimesEnd.get(3)).bus(buses.get(3)).build());
        workSchedules.add(WorkSchedule.builder().workBegin(localDateTimesBegin.get(4)).workEnd(localDateTimesEnd.get(4)).bus(buses.get(4)).build());
        workSchedules.add(WorkSchedule.builder().workBegin(localDateTimesBegin.get(5)).workEnd(localDateTimesEnd.get(5)).bus(buses.get(5)).build());
        workScheduleRepository.save(workSchedules);

        LocalDateTime local1 = LocalDateTime.of(2016,10,10,5,0);
        LocalDateTime local2 = LocalDateTime.of(2016,10,9,23,0);
    }

    private void addRandomUser() {
        User user = new User();
        user.setFirstName("Mateusz");
        user.setLastName("Nalepa");
        user.setUsername("mateusz");
        user.setPassword("password");
        user.setActive(true);
        user.setRole("ADMIN");
        userRepo.save(user);
    }
    //Dawid test
    private void addBusStop(){
    	 List<BusStop> busstops = new ArrayList<>();
    	 busstops.add(BusStop.builder().name("Slichowice").address("Slichowice 203").location(Coordinates.builder().east(50.0).north(50.0).build()).build());
    	 busStopRepository.save(busstops);
    }

}
