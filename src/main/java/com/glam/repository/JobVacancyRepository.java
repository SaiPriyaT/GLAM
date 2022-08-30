package com.glam.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glam.beans.JobVacancy;
@Repository
public interface JobVacancyRepository extends JpaRepository<JobVacancy, Integer>{

	
	@Query("select al FROM job_vacancy al WHERE al.storeID =?1")
	  List<JobVacancy> findAllJobVacanciesBystoreID(int storeID);
  

	  @Query("select al FROM job_vacancy al WHERE al.storeID =?1 and al.branchID =?2")
	  List<JobVacancy> findAllJobVacanciesByStoreIDandBranchID(int storeid,int branchid);
	  
	  
	  @Query("select al FROM job_vacancy al WHERE al.storeID =?1 and al.branchID =?2 and al.domainID =?3")
	  List<JobVacancy> findAllJobVacanciesByStoreIDBranchIDandDomainID(int storeid,int branchid, int domainid);
}
