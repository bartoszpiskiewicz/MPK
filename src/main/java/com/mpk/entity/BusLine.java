package com.mpk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class BusLine implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne(mappedBy = "busLine")
    private Route route;
    @OneToMany(mappedBy = "busLine")
    private List<Timetable> timetables;


}
