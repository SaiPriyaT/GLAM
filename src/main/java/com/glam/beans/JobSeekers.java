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
@Entity(name="job_seekers")
@Table(name="job_seekers")
public class JobSeekers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobSeekerID;
	
	private String jobSeekerName;
	private String gender;
	private String location;
	private int experience;
	
	@Column(columnDefinition = "character(1) default 'Y'::bpchar")
	private Character isActive;
	@Column(columnDefinition = "varchar(45)")
	private String createdBy;
	@Column(columnDefinition = "varchar(45)")
	private String updatedBy;
	@Column(columnDefinition = "date default now()")
	private Date createdDate;
	private Date updatedDate;
	private String email;

private long phoneNo;
private int positionID;
private String positionName;

}
