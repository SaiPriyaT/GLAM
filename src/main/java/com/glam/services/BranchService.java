
package com.glam.services;

import java.util.List;

import com.glam.beans.Branch;

public interface BranchService {

	List<Branch> getAllBranches();
	List<Branch> getAllBranchesByStoreID(int storeid);


	void saveBranch(Branch branch);

	Branch getBranchById(int branchid);

	void deleteBranchById(int branchid);
	
	 List<Branch> getBranchesByStore(Integer id);

}