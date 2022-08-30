package com.glam.repository;
  
  import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import
  org.springframework.stereotype.Repository;

import com.glam.beans.Branch;
import com.glam.beans.OurCare;
  
  @Repository 
  public interface BranchRepository extends JpaRepository<Branch, Integer> {
	  @Query(value = "SELECT al FROM branch al WHERE al.storeID =?1")
	  List<Branch> findAllBranchesByStoreID(int storeid);
  
	  @Query(value = "SELECT al FROM branch al WHERE al.storeID =?1")
	  List<Branch> findBranchesByStore(Integer id);
	  @Query("SELECT care FROM our_care care where upper(care.careType)=upper(?1)")
		List<OurCare> findAllByCareType(String careType);

	//Branch findByCareId(int careID);

/*	@Query("select care from our_care care where care.careID=?1" )
	Branch findByCareId(int careID);*/		
		
	  
  }