package com.mpk.entity;

import com.mpk.helpers.WorkScheduleHelper;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WorkSchedule implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Bus bus;
    private LocalDateTime workBegin;
    private LocalDateTime workEnd;
    private int hours;
    @OneToMany(mappedBy = "workSchedule")
    private List<Course> courses;
    //@OneToMany(mappedBy = "workSchedule")
    //private  List<BusIntersecWorkSchedule> busIntersecWorkSchedules;
}
