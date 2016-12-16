package com.mpk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class RouteBusStop {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private BusStop busStop;
    @ManyToOne
    private Route route;
    private int numberInRoute;
}
