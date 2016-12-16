package com.mpk.dao;

import com.mpk.entity.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long> {
    List<WorkSchedule> findByWorkBeginGreaterThanEqualAndWorkEndLessThanEqual(LocalDateTime workBegin, LocalDateTime workEnd);
}
