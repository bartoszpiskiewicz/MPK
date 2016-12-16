package com.mpk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.mpk.enums.BusStatus;

import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusStop implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    @Embedded
    private Coordinates location;
    @OneToMany(mappedBy = "busStop")
    private List<RouteBusStop> routeBusStops;

}
