package com.glam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glam.beans.Vendors;
import com.glam.repository.VendorRepository;
@Service

public class VendorServiceImp implements VendorService{
	
	 @Autowired
	 VendorRepository vendorRepository;
	

	@Override
	public void addVendor(Vendors vendors) {
		// TODO Auto-generated method stub
		
		vendorRepository.save(vendors);
	}


	@Override
	public List<Vendors> getAllVendorsByStoreIDandBranchID(int storeid,int branchid) {
		// TODO Auto-generated method stub
		return vendorRepository.findAllVendorsByStoreIDandBranchID(storeid,branchid);
	}


	@Override
	public Vendors getVendorId(int vendorID) {
		// TODO Auto-generated method stub
		return vendorRepository.getById(vendorID);
	}


	@Override
	public List<Vendors> getAllVendorsByStoreID(int storeID) {
		// TODO Auto-generated method stub
		return vendorRepository.findAllVendorsByStoreID(storeID);
	}


	@Override
	public List<Vendors> listAllVendors() {
		// TODO Auto-generated method stub
		return vendorRepository.findAll();
	}


	

}
