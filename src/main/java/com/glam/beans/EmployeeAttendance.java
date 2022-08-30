package com.glam.beans;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity(name = "employee_Attendance")
@Table(name  = "employee_Attendance")
public class EmployeeAttendance {
	


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int attendaceID;
	
	private LocalDate date;
	
	private LocalTime CheckIn;
	
    private LocalTime CheckOut;
    
	@Column(name = "employee_id", nullable = true)
    private int employeeId;
    
    private String HoursWorked;
    
    private String employeeName;
    
	@Column(name = "storeid", nullable = true)
	private int storeID;
	
	@Column(name = "branchid", nullable = true)
	private int branchID;
	
	private String storeName;
	
	private String branchName;
	
	@Column(name = "domainid", nullable = true)
	private int domainID;
	
	private String domainName;
	
	



	@Column(columnDefinition = "character(1) default 'Y'::bpchar")
	private Character isActive;
	@Column(columnDefinition = "varchar(45)")
	private String createdBy;
	@Column(columnDefinition = "varchar(45)")
	private String updatedBy;
	@Column(columnDefinition = "date default now()")
	private Date createdDate;

	private Date updatedDate;
	
	

}
