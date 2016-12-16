package com.mpk.entity;

import com.mpk.enums.DriverStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Driver implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private double salary;
    @OneToMany(mappedBy = "driver")
    private List<WorkSchedule> workSchedules;
    @OneToOne(mappedBy = "driver")
    private User user;
    @Enumerated(EnumType.STRING)
    private DriverStatus status;
}
