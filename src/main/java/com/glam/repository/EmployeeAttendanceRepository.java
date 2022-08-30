package com.glam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glam.beans.EmployeeAttendance;
@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance,Integer> {

}
