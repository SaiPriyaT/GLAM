package com.glam.beans;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


	@Data
	@Entity(name = "vendors")
	@Table(name = "vendors")
	public class Vendors {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int vendorID;

		private String vendorName;
		private String vendorAddress;
		private long  vendorMobile;
		private String storeName;
		private String branchName;
		private int storeID;
		
		private int branchID;

}
