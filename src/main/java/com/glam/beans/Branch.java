package com.glam.beans;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "branch")
@Table(name = "branch")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int branchID;
	private String branchName;
	
    private int storeID;
	private String description;
	private String branchAddress;
	
	@Column(columnDefinition = "character(1) default 'Y'::bpchar")
	private Character isActive;
	@Column(columnDefinition = "varchar(45)")
	private String createdBy;
	@Column(columnDefinition = "varchar(45)")
	private String updatedBy;
	@Column(columnDefinition = "date default now()")
	private Date createdDate;
	
	private Date updatedDate;
	
	private String storeName;
	@Column(name = "latitude", nullable = true)
	private double latitude;

	@Column(name = "longitude", nullable = true)
	private double longitude;
	
}
