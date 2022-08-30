package com.glam.beans;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "our_care")
@Table(name = "our_care")
public class OurCare {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int careID;
	@Column(name = "comboid", nullable = true)
	private int comboID;

	@Column(name = "subscriberid", nullable = true)
	private int subscriberID;

	@Column(name = "storeid", nullable = true)
	private int storeID;

	@Column(name = "branchid", nullable = true)
	private int branchID;

	private String careName;
	private String careType;
	private String careCategory;
	
	@Column(name = "care_price", nullable = true)
	private double carePrice;
	
	@Column(name = "care_duration", nullable = true)
	private int careDuration;
	@Column(columnDefinition = "character(1) default 'Y'::bpchar")
	private Character isActive;
	@Column(columnDefinition = "varchar(45)")
	private String createdBy;
	@Column(columnDefinition = "varchar(45)")
	private String updatedBy;
	@Column(columnDefinition = "date default now()")
	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

	private String storeName;
	private String branchName;

	private String careDescription;
	@Lob
	private String data;

}
