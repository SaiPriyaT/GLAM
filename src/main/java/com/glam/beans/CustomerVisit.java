package com.glam.beans;

import java.time.LocalDate;
import java.util.Date;

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

@Data
@Entity(name = "customer_visit")
@Table(name = "customer_visit")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVisit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerID;
    private String customerName;
	private String gender;
	private String customerLocation;
	private String customerEmailID;
	private String password;
	@Column(name = "customerMobile", nullable = true)
	public long customerMobile;
	private String Captcha;
	private String userCaptcha;
	
	@Column(name = "subscriberid", nullable = true)
	private int subscriberID;
	private String subscriptionType;
	private LocalDate subscriptionDate;
	private LocalDate expireDate;
	private Double Price;
	private int Validity;

	

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
