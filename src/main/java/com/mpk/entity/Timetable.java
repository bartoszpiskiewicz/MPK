package com.mpk.entity;

import com.mpk.enums.DayKind;
import com.mpk.enums.Direction;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class Timetable implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private BusLine busLine;
    private Direction direction;
    @Enumerated(EnumType.STRING)
    private DayKind dayKind;
    @Temporal(TemporalType.TIME)
    private Date departureTime;
}
