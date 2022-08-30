package com.glam.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "employee_general")
@Table(name = "employee_general")

public class EmployeeGeneral {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;

	@Lob
	private String employeeImage;

	private String employeeName;
	@Column(name = "employee_age", nullable = true)
	private int employeeAge;
	@Column(name = "storeid", nullable = true)
	public int storeID;
	@Column(name = "branchid", nullable = true)
	public int branchID;
	@Column(name = "domainid", nullable = true)
	public int domainID;

	private String employeePermanentAddress;

	private String employeeCurrentAddress;
	@Column(name = "employee_mobile", nullable = true)
	private long employeeMobile;


	private String employeePassword;
	private String storeName;
	private String branchName;
	private String domainName;
	@Column(name = "employee_experience", nullable = true)
	private int employeeExperience;
	
	private String employeeGender;

	
	private String employeeEmailId;

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
