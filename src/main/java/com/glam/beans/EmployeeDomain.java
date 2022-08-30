package com.glam.beans;

import java.util.Date;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity(name="employee_Domain")
@Table(name="employee_Domain")


public class EmployeeDomain {
	
	
	

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int domainID;

@Column(name = "storeid", nullable = true)
public int storeID;

@Column(name = "branchid", nullable = true)
public int branchID;


public String domainName;
public int domainStrength;
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
private String branchName;
}
