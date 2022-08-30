package com.glam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glam.beans.Branch;
import com.glam.beans.CustomerAppointment;
import com.glam.beans.OurCare;

@Repository

public interface CustomerAppointmentRepository extends JpaRepository<CustomerAppointment, Integer>{
	/*
	 * @Query(
	 * value="select al from Appointment al where al.employee.employeeId=?1 and al.date=?2"
	 * ) List<CustomerAppointment> appointmentListByEmployeeid(int employeeId,String
	 * date);
	 */
	@Query("select care from our_care care where care.careID=?1" )
	OurCare findByCareId(int careID);		
	@Query("SELECT care FROM our_care care")
	List<OurCare> findAllList();
	
	@Query("select distinct careType from our_care")
	List<String> findDistinctCare(String careType);
	
    @Query("select care from our_care care where care.careName=?1")
	List<OurCare> findService(String careName);
    
	}