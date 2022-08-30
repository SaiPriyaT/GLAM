
package com.glam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glam.beans.Branch;
import com.glam.repository.BranchRepository;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchRepository branchRepository;

	@Override 
	public List<Branch> getAllBranches() { 
		// TODO Auto-generated method stub
   return branchRepository.findAll(); }

	@Override 
	public void saveBranch(Branch branch) {
		// TODO Auto-generated method stub
   this.branchRepository.save(branch);
  
  }

	

	@Override
	public void deleteBranchById(int branchid) {
		// TODO Auto-generated method stub
		this.branchRepository.deleteById(branchid);
		
	}
	@Override
	public List<Branch> getAllBranchesByStoreID(int storeid) {
	return branchRepository.findAllBranchesByStoreID(storeid);
	}

	@Override
	public  List<Branch> getBranchesByStore(Integer id) {
		// TODO Auto-generated method stub
		return branchRepository.findBranchesByStore(id);
	}

	@Override
	public Branch getBranchById(int branchid) {
		// TODO Auto-generated method stub
		return branchRepository.getById(branchid);
	}

}