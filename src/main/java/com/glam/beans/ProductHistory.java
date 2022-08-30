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
	@Entity(name = "producthistory")
	@Table(name = "producthistory")
	public class ProductHistory {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int trackId;
		private int productId;
		private int VendorId;
		@Column(nullable=true)
		private String productName;
		
		@Column(nullable=true)
		private double productPrice;
		
		@Column(nullable=true)
		private String productQuantity;
		private int Units;
		
		private int storeID;
		private int branchID;
		private int modifiedUnits;

		@Column(nullable=true)
		private String productBrand;
		@Column(nullable=true)
		private String productDetails;
		private String action;
		@Column(nullable=true)
		private int previousUnits;
		@Column(nullable=true)
		private double actualPrice;
		@Column(nullable=true)
		private double soldPrice;
		@Column(nullable=true)
		private String customerName ;
		@Column(nullable=true)
		private Long CustomerNum;
		
		@Column(nullable=true)
		private double bill;
		
	
		private LocalDateTime createdDate;
		
		@Column(columnDefinition = "character(1) default 'Y'::bpchar")
		private Character isActive;
		private int categoryId;
		@Column(nullable=true)
		private String categoryName;
		
	
		
		

	}
