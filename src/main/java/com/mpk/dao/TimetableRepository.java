package com.mpk.dao;

import com.mpk.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rower on 06.11.2016.
 */
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
}
