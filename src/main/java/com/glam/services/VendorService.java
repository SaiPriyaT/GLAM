package com.glam.services;

import java.util.List;

import com.glam.beans.Vendors;

public interface VendorService {

	void addVendor(Vendors vendors);

	List<Vendors> getAllVendorsByStoreIDandBranchID(int storeID, int branchID);

	Vendors getVendorId(int vendorID);

	List<Vendors> getAllVendorsByStoreID(int storeID);

	List<Vendors> listAllVendors();



}
