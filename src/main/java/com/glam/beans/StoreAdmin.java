package com.glam.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "store_admin")
@Table(name = "store_admin")
public class StoreAdmin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminID;
	private String adminName;
	private String adminEmail;
	private String adminPassword;
	private String adminMobile;

	@Column(columnDefinition = "character(1) default 'Y'::bpchar")
	private Character isActive;
	@Column(columnDefinition = "varchar(45)")
	private String createdBy;
	@Column(columnDefinition = "varchar(45)")
	private String updatedBy;
	@Column(columnDefinition = "date default now()")
	private Date createdDate;

	private Date updatedDate;
	private String captcha;

	private String adminCaptcha;

}