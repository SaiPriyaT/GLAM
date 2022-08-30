package com.glam.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity(name = "employee_Appointments")
@Table(name = "employee_Appointments")
public class EmployeeAppointments {
	
	@Column(name = "expertiseid", nullable = true)
	private int expertiseID;
	
	@Column(name = "employee_id", nullable = true)
	private int employeeId;
	
	@Column(name = "appointmentid", nullable = true)
	private int appointmentID;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int empAppointmentSno;
	
	@Column(name = "storeid", nullable = true)
    private int storeID;
	
	@Column(name = "branchid", nullable = true)
    private int branchID;
	
	private char empAttendanceStatus;

	private Date dayOftheWeek;
	
	private char morningSlotAvailability;

	private char afternoonSlotAvailability;
	
	private char eveningSlotAvailability;
	@Column(columnDefinition = "character(1) default 'Y'::bpchar")
	private char isActive;
	@Column(columnDefinition = "varchar(45)")
	private String createdBy;
	@Column(columnDefinition = "varchar(45)")
	private String updatedBy;
	@Column(columnDefinition = "date default now()")
	private Date createdDate;

	private Date updatedDate;
	

}