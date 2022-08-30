   package com.glam.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.apache.commons.codec.language.DoubleMetaphone.DoubleMetaphoneResult;

import lombok.Data;

@Data
@Entity(name = "bills")
@Table(name = "bills")
public class Bills {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int billID;

	private String billName;
	private String billCategory;
	private double bookingCharges;
	
	@Column(name = "billamount", nullable = true)
	private double billAmount;
	
	@Column(name = "employeeid", nullable = true)
	private int employeeId;
	
	@Column(name = "branchid", nullable = true)
	private int branchID;
	
	@Column(name = "customerid", nullable = true)
	private int customerID;
	
	
	@Column(name = "productsid", nullable = true)
	private int productsID;
	private String careName;
	@Column(name = "care_price", nullable = true)
	private double carePrice;
	

	private int appointmentID;

	@Column(name = "care_duration", nullable = true)
	private int careDuration;
	@Column(columnDefinition = "character(1) default 'Y'::bpchar")
	private Character isActive;
	@Column(columnDefinition = "varchar(45)")
	private String createdBy;
	@Column(columnDefinition = "varchar(45)")
	private String updatedBy;
	@Column(columnDefinition = "date default now()")
	private Date createdDate;

	private Date updatedDate;
	
	@Column(name = "storeid", nullable = true)
	private int storeID;

	@Column(name = "location", nullable = true)
	private String location;
	@Column(name = "customername", nullable = true)
	private String customerName;
	}


