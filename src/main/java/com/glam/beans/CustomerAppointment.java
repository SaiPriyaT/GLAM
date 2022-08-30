package com.glam.beans;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity(name="customer_appointment")
@Table(name="customer_appointment")
public class CustomerAppointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appointmentID;
	@Column(name = "customerid", nullable = true)
	private int customerID;
	private String customerName;
	private String customerEmailID;
	private long customerMobile;
	private String customerLocation;
	private String appointmentDate;
	private LocalDate appointmentBookedDate;
	
	private String careType;
	private String careName;
	private String gender;
	private String carePrice;
	private String storeName;
	private String branchName;
	private String careDuration;
	private String careID;
	private String careCategory;
    private int storeID;
    private int branchID;
}
