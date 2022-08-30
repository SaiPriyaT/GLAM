package com.glam.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; 
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "store")
@Table(name = "store")
public class Store {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int storeID;
	
	private String storeName;
	
	@Lob
	private String storeImage;
	@Lob
	private String storeVideo;
	
	private String storeAddress;
	private String description;
	private String storedescription;
	private String storeLink;
	private String appStoreLink;
	private String adminMobile;
	private String adminEmail;
	@Column(columnDefinition = "character(1) default 'Y'::bpchar")
	private Character isActive;
	@Column(columnDefinition = "varchar(45)")
	private String createdBy;
	@Column(columnDefinition = "varchar(45)")
	private String updatedBy;
	@Column(columnDefinition = "date default now()")
	private Date createdDate;
	
	private Date updatedDate;	
	
}