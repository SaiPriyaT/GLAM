package com.glam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.glam.beans.Vendors;

public interface VendorRepository extends JpaRepository<Vendors, Integer>{
	@Query("select al FROM vendors al WHERE al.storeID =?1 and al.branchID =?2")
	  List<Vendors> findAllVendorsByStoreIDandBranchID(int storeid,int branchid);
	


	@Query("select al FROM vendors al WHERE al.storeID =?1")
	 List<Vendors> findAllVendorsByStoreID(int storeid);
	
	
	
}
