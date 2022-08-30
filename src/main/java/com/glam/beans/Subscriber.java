package com.glam.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity(name="subscriber")
@Table(name="subscriber")
@Data
public class Subscriber {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int subscriberID;
	
	private String subscriptionType;
	private int discount;
	private String Description;
	private Double price;
	@Column(columnDefinition = "character(1) default 'Y'::bpchar")
	private Character isActive;
	@Column(columnDefinition = "varchar(45)")
	private String createdBy;
	@Column(columnDefinition = "varchar(45)")
	private String updatedBy;
	@Column(columnDefinition = "date default now()")
	private Date createdDate;
	
	private Date updatedDate;	
	private int Validity;
	
	

}
