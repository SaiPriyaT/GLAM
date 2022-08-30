package com.glam.beans;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity(name="social_networks")
@Table(name="social_networks")
@Data
public class SocialNetworks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer networkId;
	private String networkName;
	private String networkLink;
	@Lob
	private String icon;
	
	private char isActive;
	
	
	



	

}
