package com.glam.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name="job_vacancy")
@Table(name="job_vacancy")
@Data
public class JobVacancy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int positionID;
	private String positionName;
	@Column(name = "experience", nullable = true)
	private int Experience;
	@Column(name = "salary",nullable = true)
	private int Salary;
	
	private int positionsRequired;
	private String Gender;

	private String Description;
	private Character isActive;
	private Date createdDate;
	private String createdBy;
	@Column(name = "storeid", nullable = true)
	private int storeID;
	@Column(name = "branchid", nullable = true)
	private int branchID;
	private String storeName;
	private String branchName;
	
	private int domainID;
	private String domainName;
}
