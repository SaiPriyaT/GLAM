package com.glam.beans;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "product")
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	private int vendorID;
	@Column(nullable=true)
	private String productName;
	private int storeID;
	private int branchID;
	@Column(nullable=true)
	private double productPrice;
	@Column(nullable=true)
	private String productQuantity;
	private int Units;
	private int PreviousUnits;

	@Column(nullable=true)
	private String productBrand;
	@Column(nullable=true)
	private String productDetails;
	
	private String action;
	
	
	private LocalDateTime createdDate;

	
	@Column(columnDefinition = "character(1) default 'Y'::bpchar")
	private Character isActive;
	private int categoryId;
	@Column(nullable=true)
	private String categoryName;
	public void setUri(List<String> readAllLines) {
		// TODO Auto-generated method stub
		
	}
	

}
