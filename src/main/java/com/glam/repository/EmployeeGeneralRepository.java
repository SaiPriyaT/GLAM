package com.glam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glam.beans.EmployeeGeneral;


@Repository

public interface EmployeeGeneralRepository extends JpaRepository<EmployeeGeneral, Integer> {
	
	
    @Query("select al from employee_general al where al.employeeEmailId = ?1 and al.employeePassword=?2")
	EmployeeGeneral findByemployeeEmailIdandemployeePassword(String employeeEmailId, String employeePassword);
    
    @Query("select al.employeeEmailId from employee_general al where al.employeeEmailId=?1")
    String findByEmployeeEmailIdIgnoreCase(String employeeEmailId);
    
    @Query("select al FROM employee_general al WHERE al.storeID =?1")
	  List<EmployeeGeneral> findAllEmployeesBystoreID(int storeID);
    

	  @Query("select al FROM employee_general al WHERE al.storeID =?1 and al.branchID =?2")
	  List<EmployeeGeneral> findAllEmployeesByStoreIDandBranchID(int storeid,int branchid);
	  
	  
	  @Query("select al FROM employee_general al WHERE  al.domainID =?1 and al.branchID =?2")
	  List<EmployeeGeneral> findAllEmployeesByDomainIDandBranchID(int domainid,int branchid);
	  
	  
	  @Query("select al FROM employee_general al WHERE al.storeID =?1 and al.branchID =?2 and al.domainID =?3")
	  List<EmployeeGeneral> findAllEmployeesByStoreIDBranchIDandDomainID(int storeid,int branchid, int domainid);
	  
	  @Query("select a from employee_general a WHERE lower(a.employeeEmailId)=lower(?1)")
	  public EmployeeGeneral findByEmail(String employeeEmailId);
	  
    }
