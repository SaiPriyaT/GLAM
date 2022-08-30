package com.glam.beans;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity(name="rating")
@Table(name="rating")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ratingID;
	@Column(name = "storeid", nullable = true)
    private int storeID;
	@Column(name = "branchid", nullable = true)
    private int branchID;
	@Column(name = "customerid", nullable = true)
	private int customerID;
	@Column(name = "employeeid", nullable = true)
	private int employeeId;
	@Column(name = "employee_rating", nullable = true)
	private int employeeRating;
	
	@Column(name = "store_rating", nullable = true)
	private int storeRating;
	
	private String feedBack;
	@Column(columnDefinition = "character(1) default 'Y'::bpchar")
	private Character isActive;
	@Column(columnDefinition = "varchar(45)")
	private String createdBy;
	@Column(columnDefinition = "varchar(45)")
	private String updatedBy;
	@Column(columnDefinition = "date default now()")
	private LocalDate createdDate;
	
	private Date updatedDate;
	
	

}
