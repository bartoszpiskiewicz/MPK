package com.mpk.services;

import com.mpk.dao.BusLineRepository;
import com.mpk.dao.BusStopRepository;
import com.mpk.dao.RouteRepository;
import com.mpk.entity.BusLine;
import com.mpk.entity.BusStop;
import com.mpk.entity.Route;
import com.mpk.entity.RouteBusStop;
import com.mpk.excepctions.BusLineNotFoundException;
import com.mpk.excepctions.ExceptionFactory;
import com.mpk.excepctions.RouteNotFoundException;
import com.mpk.helpers.BusStopInRouteHelper;
import com.mpk.helpers.RouteHelper;
import com.mpk.services.timetable.TimetableAtBusStopFlyweight;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Service
public class RouteService extends Observable {
    private RouteRepository routeRepository;
    private BusLineRepository busLineRepository;
    private BusStopRepository busStopRepository;
    @Autowired
    public RouteService(RouteRepository routeRepository, BusLineRepository busLineRepository, BusStopRepository busStopRepository, TimetableAtBusStopFlyweight timetableAtBusStopFlyweight) {
        this.routeRepository = routeRepository;
        this.busLineRepository = busLineRepository;
        this.busStopRepository = busStopRepository;
        this.addObserver(timetableAtBusStopFlyweight);
    }

    public RouteHelper findByBusLine(Long busLineId) {
        BusLine busLine = busLineRepository.findOne(busLineId);
        ExceptionFactory.throwNotFoundExceptionIfNull(busLine, BusLine.class);
        List<Route> routes = routeRepository.findByBusLine(busLine);
        Route r = routes.get(0);
        RouteHelper routeHelper = new RouteHelper();
        routeHelper.setId(r.getId());
        routeHelper.setBusStops(fetchBusStopsInHelpers(r.getRouteBusStops()));
        return routeHelper;
    }
//
//    public RouteHelper findById(Long routeId) {
//        Route route = routeRepository.findOne(routeId);
//        ExceptionFactory.throwNotFoundExceptionIfNull(route);
//        RouteHelper routeHelper = new RouteHelper();
//        routeHelper.setId(route.getId());
//        routeHelper.setBusStops(fetchBusStopsInHelpers(route.getRouteBusStops()));
//        return routeHelper;
//    }

    public void add(Long busLineId, RouteHelper routeHelper) {
        BusLine busLine = busLineRepository.findOne(busLineId);
        ExceptionFactory.throwNotFoundExceptionIfNull(busLine, BusLine.class);
        Route route = new Route();
        route.setRouteBusStops(getRouteBusStops(routeHelper.getBusStops()));
        routeRepository.save(route);
        this.notifyObservers();
    }

    public void update(Long busLineId, RouteHelper routeHelper) {
        BusLine busLine = busLineRepository.findOne(busLineId);
        ExceptionFactory.throwNotFoundExceptionIfNull(busLine, BusLine.class);
        Route route = new Route();
        busLine.setRoute(route);
        route.setId(routeHelper.getId());
        route.setRouteBusStops(getRouteBusStops(routeHelper.getBusStops()));
        routeRepository.save(route);
        busLineRepository.save(busLine);
        this.notifyObservers();
    }

    public void delete(Long routeId) {
        Route route = routeRepository.findOne(routeId);
        ExceptionFactory.throwNotFoundExceptionIfNull(route, Route.class);
        routeRepository.delete(route);
        this.notifyObservers();
    }

    private List<RouteBusStop> getRouteBusStops(List<BusStopInRouteHelper> busStops) {
        List<RouteBusStop> routeBusStops = new ArrayList<>();
        for (BusStopInRouteHelper b : busStops) {
            routeBusStops.add(getRouteBusStop(b));
        }
        return routeBusStops;
    }

    private RouteBusStop getRouteBusStop(BusStopInRouteHelper busStopInRouteHelper) {
        RouteBusStop routeBusStop = new RouteBusStop();
        routeBusStop.setNumberInRoute(busStopInRouteHelper.getNumberInRoute());
        BusStop busStop = busStopRepository.findOne(busStopInRouteHelper.getId());
        routeBusStop.setBusStop(busStop);
        return routeBusStop;
    }

    private List<BusStopInRouteHelper> fetchBusStopsInHelpers(List<RouteBusStop> routeBusStops) {
        List<BusStopInRouteHelper> busStopInRouteHelpers = new ArrayList<>();
        for (RouteBusStop b : routeBusStops) {
            BusStopInRouteHelper busStopInRouteHelper = new BusStopInRouteHelper();
            busStopInRouteHelper.setId(b.getBusStop().getId());
            busStopInRouteHelper.setName(b.getBusStop().getName());
            busStopInRouteHelper.setLocation(b.getBusStop().getLocation());
            busStopInRouteHelper.setAddress(b.getBusStop().getAddress());
            busStopInRouteHelper.setNumberInRoute(b.getNumberInRoute());
            busStopInRouteHelpers.add(busStopInRouteHelper);
        }
        return busStopInRouteHelpers;
    }

}
