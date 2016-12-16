package com.mpk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
public class Route implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "route")
    @OrderBy("numberInRoute")
    private List<RouteBusStop> routeBusStops;
    @ManyToOne
    private BusLine busLine;
}
