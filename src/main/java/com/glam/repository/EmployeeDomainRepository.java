package com.glam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glam.beans.EmployeeDomain;
@Repository 
public interface EmployeeDomainRepository extends JpaRepository<EmployeeDomain,Integer> {
	
	@Query("select al FROM employee_Domain al WHERE al.storeID =?1 and al.branchID =?2")
	  List<EmployeeDomain> findAllDomainsByStoreIDandBranchID(int storeid,int branchid);
	
	
	@Query("select al FROM employee_Domain al WHERE al.storeID =?1")
	List<EmployeeDomain> findAllDomainsByStoreID(int storeid);
	
	
	@Query("select al FROM employee_Domain al WHERE al.branchID =?1")
	List<EmployeeDomain> findAllDomainsByBranchID(int branchid);

}
