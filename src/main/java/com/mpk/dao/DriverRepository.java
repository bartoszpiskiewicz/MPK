package com.mpk.dao;

import com.mpk.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DriverRepository extends JpaRepository<Driver, Long> {
}
