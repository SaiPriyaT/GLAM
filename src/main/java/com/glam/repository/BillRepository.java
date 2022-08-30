package com.glam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glam.beans.Bills;
@Repository
public interface BillRepository extends JpaRepository<Bills, Integer> {
	
	@Query("select bills from bills bills where bills.appointmentID=?1")
	List<Bills> findBillsByAppointmentId(int appointmentID);


}
