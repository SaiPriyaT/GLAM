package com.glam.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.glam.beans.SocialNetworks;



public interface SocialNetworkService {
	
	List<SocialNetworks> getAllNetworks();

	SocialNetworks addNetwork(SocialNetworks network);

	void updateNetwork(SocialNetworks network);

	void deleteNetworkById(int networkId);

	SocialNetworks getNetworkById(int networkId);
	
	void addService(MultipartFile file, SocialNetworks network);

}
