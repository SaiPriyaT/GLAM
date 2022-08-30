package com.glam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.glam.beans.SocialNetworks;
import com.glam.repository.SocialNetworkRepository;



@Service
public class SocialNetworkServiceImpl implements SocialNetworkService{
	
	@Autowired
	
	SocialNetworkRepository socialNetworkRepository;

	@Override
	public List<SocialNetworks> getAllNetworks() {
		// TODO Auto-generated method stub
		return socialNetworkRepository.findAll();
	}

	@Override
	public SocialNetworks addNetwork(SocialNetworks network) {
		// TODO Auto-generated method stub
		return socialNetworkRepository.save(network);
	}

	@Override
	public void updateNetwork(SocialNetworks network) {
		// TODO Auto-generated method stub
		
		socialNetworkRepository.save(network);
	}

	@Override
	public void deleteNetworkById(int networkId) {
		// TODO Auto-generated method stub
		socialNetworkRepository.deleteById(networkId);
	}

	@Override
	public SocialNetworks getNetworkById(int networkId) {
		// TODO Auto-generated method stub
		return socialNetworkRepository.findById(networkId).get();
	}

	@Override
	public void addService(MultipartFile file, SocialNetworks network) {
		// TODO Auto-generated method stub
		
		
		socialNetworkRepository.save(network);
		
		
	}

}
