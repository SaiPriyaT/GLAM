package com.glam.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity(name="production")
@Table(name="production")
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int productID;
	
private String productName;
private String productCategory;
@Column(name = "no_of_units_sold", nullable = true)
private int noOfUnitsSold;

@Column(name = "unit_sold_cost", nullable = true)
private double unitSoldCost;

@Column(name = "no_of_units_purchased", nullable = true)
private int noOfUnitsPurchased;	

@Column(name = "unit_purchased_price", nullable = true)
private double unitPurchasedPrice;

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

@Column(name = "branchid", nullable = true)
private int branchID;



}
