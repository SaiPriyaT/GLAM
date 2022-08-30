package com.glam.beans;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;
@Data
@Entity(name="combos")
@Table(name="combos")
public class Combo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int comboID;
//	@OneToMany(mappedBy = "combos",cascade =CascadeType.ALL)
//	private Set<OurCare> ourCare;	
	
	
	private String comboName;
	private String comboItems;
	
	private double comboPrice;
	private String comboDescription;
	//private double comboDiscount;	
//	private int subscriptionID;

	
	@Column(columnDefinition = "character(1) default 'Y'::bpchar")
	private Character isActive;
	@Column(columnDefinition = "varchar(45)")
	private String createdBy;
	@Column(columnDefinition = "varchar(45)")
	private String updatedBy;
	@Column(columnDefinition = "date default now()")
	private Date createdDate;
	private Date startDate;
	private Date endDate;
	private Date updatedDate;
	private String careName;
	//private int comboDuration;
	private String storeName;
	private String careType;
//	
//	@NotFound(action = NotFoundAction.IGNORE)
//	@ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.LAZY,optional = true)
    private int storeID;
//	
//	@NotFound(action = NotFoundAction.IGNORE)
//	@ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.LAZY,optional = true)
    private int branchID;

	
	}